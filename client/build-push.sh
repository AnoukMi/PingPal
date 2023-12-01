IMAGE=eu.gcr.io/mightycode/PingPal_client
npm install
npm run build
docker build . -t $IMAGE
if [[ $1 == "--push" ]]
then
  docker push $IMAGE
fi
