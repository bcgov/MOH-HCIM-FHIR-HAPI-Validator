#!/usr/bin/env bash
# get_Npm_CA_Baseline.sh
# Usage: ./get_Npm_CA_Baseline.sh

set -euo pipefail

REGISTRY="https://packages.simplifier.net"
PACKAGE="hl7.fhir.ca.baseline@1.2.0"

# relative destination inside Java project
RES_DIR="src/main/resources"

# record current working dir (project root assumed)
BASE_DIR="$(pwd)"

# Save current npm strict-ssl setting (could be 'true'/'false' or empty)
orig_ssl="$(npm config get strict-ssl 2>/dev/null || echo "undefined")"

# cleanup & restore on exit (success or failure)
tmpdir=""
cleanup() {
  # remove tmpdir if created
  if [ -n "$tmpdir" ] && [ -d "$tmpdir" ]; then
    rm -rf "$tmpdir" || true
  fi

  # restore strict-ssl
  if [ "$orig_ssl" = "undefined" ] || [ -z "$orig_ssl" ]; then
    npm config delete strict-ssl 2>/dev/null || true
  else
    npm config set strict-ssl "$orig_ssl" >/dev/null 2>&1 || true
  fi
}
trap cleanup EXIT

# Temporarily turn off strict-ssl (turn back on when the script ends or fails)
echo "Saving current npm strict-ssl: $orig_ssl"
echo "Turning off npm strict-ssl temporarily..."
npm config set strict-ssl false

# Run install (no-save to avoid modifying package.json)
echo "Running: npm --registry \"$REGISTRY\" install \"$PACKAGE\" --no-save"
npm --registry "$REGISTRY" install "$PACKAGE" --no-save

# Create TGZ using npm pack in a temporary directory (relative paths preserved)
tmpdir="$(mktemp -d)"
echo "Packing package in temporary dir: $tmpdir"
pushd "$tmpdir" >/dev/null

# npm pack will fetch the package from registry and write a .tgz into the cwd
npm pack "$PACKAGE" --registry "$REGISTRY"

# find the resulting tgz (first *.tgz)
tgz_file="$(ls -1 *.tgz | head -n1 || true)"
if [ -z "$tgz_file" ]; then
  echo "ERROR: npm pack did not produce a .tgz"
  popd >/dev/null
  exit 3
fi

popd >/dev/null

# Extract base package name (remove version after last @)
pkg_no_version="${PACKAGE%@*}"

# Handle scoped packages like @scope/pkg
# Remove leading @ and replace / with -
clean_name="$(echo "$pkg_no_version" | sed 's/^@//' | sed 's/\//-/g')"

final_name="${clean_name}.tgz"

# ensure destination exists, then move the tarball there (relative path)
mkdir -p "$BASE_DIR/$RES_DIR"
mv "$tmpdir/$tgz_file" "$BASE_DIR/$RES_DIR/$final_name"

echo "TGZ created: $RES_DIR/$final_name"