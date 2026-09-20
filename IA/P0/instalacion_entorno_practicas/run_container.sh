#!/bin/bash

IMAGE_NAME="grei_ia"

sudo docker run --user root -e NB_UID=$(id -u) -e NB_GID=$(id -g) -e CHOWN_HOME=yes -e CHOWN_HOME_OPTS='-R' -it --rm --name="IntelixenciaArtificial" -v "${PWD}":/home/jovyan/ -p 8888:8888 ${IMAGE_NAME} start-notebook.sh --NotebookApp.token=''

