#!/usr/bin/env bash

PROTO_DIR="src/main/proto"
PROTO_JS_OUT_DIR="src/main/webpack"

[ -d $PROTO_JS_OUT_DIR ] || mkdir -p $PROTO_JS_OUT_DIR

echo "before :"
gls -l --time-style=full-iso $PROTO_JS_OUT_DIR/ping
rm -rf $PROTO_JS_OUT_DIR/ping

for PROTO in $(ls $PROTO_DIR/ping)
do
    protoc -I=$PROTO_DIR ping/$PROTO --js_out=import_style=commonjs:$PROTO_JS_OUT_DIR
    protoc -I=$PROTO_DIR ping/$PROTO --grpc-web_out=import_style=commonjs,mode=grpcweb:$PROTO_JS_OUT_DIR
done

echo
echo "after :"
gls -l --time-style=full-iso $PROTO_JS_OUT_DIR/ping

#echo
#echo "git status :"
#git status $JS_OUT_DIR/ping
#
#cd $JS_OUT_DIR
#npm clean-install
#npx webpack grpc.js
