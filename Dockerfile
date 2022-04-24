FROM openjdk:11

# https://stackoverflow.com/a/58571881
RUN apt-get update
RUN rm /bin/sh && ln -s /bin/bash /bin/sh
RUN apt-get -qq -y install unzip zip

RUN curl -s "https://get.sdkman.io" | bash
RUN source "$HOME/.sdkman/bin/sdkman-init.sh"; sdk install kotlin # https://stackoverflow.com/a/62189028
