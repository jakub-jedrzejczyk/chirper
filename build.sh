function main() {
    cd ./chirper-post/; sh ./build.sh; cd ..
    cd ./chirper-user/; sh ./build.sh; cd ..
    cd ./chirper-gateway/; sh ./build.sh; cd ..
    cd ./chirper-ng/; sh ./build.sh; cd ..
}

main "$@"
