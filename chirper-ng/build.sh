#!/usr/bin/env bash

function main() {
  npm install
  npm run build
  title="$(grep -n "org.opencontainers.image.title" Dockerfile | cut -f2 -d "=" | xargs)"
  version="$(grep -n "org.opencontainers.image.version" Dockerfile | cut -f2 -d "=" | xargs)"
  sudo docker build \
    --label "org.opencontainers.image.created=$(date +%Y-%m-%dT%H:%M:%S%:z)" \
    -t "${title}":"${version}" .
}

main "$@"
