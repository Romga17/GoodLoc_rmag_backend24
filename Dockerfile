FROM ubuntu:latest
LABEL authors="stagiaire"

ENTRYPOINT ["top", "-b"]