IMAGE=eu.gcr.io/mightycode/PingPal_server
mvn package -DskipTests=true
docker build . -t $IMAGE
if [[ $1 == "--push" ]]
then
  docker push $IMAGE
fi
