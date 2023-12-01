IMAGE=eu.gcr.io/mightycode/cpoo_server
mvn package -DskipTests=true
docker build . -t $IMAGE
if [[ $1 == "--push" ]]
then
  docker push $IMAGE
fi
