FROM kbase/kbase:sdkbase.latest
MAINTAINER John-Marc Chandonia
# -----------------------------------------

# Insert apt-get instructions here to install
# any required dependencies for your module.

# RUN apt-get update

RUN apt-get update
RUN apt-get upgrade --yes
RUN add-apt-repository ppa:ubuntu-toolchain-r/test --yes
RUN apt-get update
RUN apt-get install libstdc++6 seqtk --yes

WORKDIR /kb/module
RUN mkdir -p /kb/module/dependencies/bin

WORKDIR /kb/module/dependencies/bin
RUN curl -L https://github.com/torognes/vsearch/releases/download/v2.3.0/vsearch-2.3.0-linux-x86_64.tar.gz -o vsearch-2.3.0-linux-x86_64.tar.gz && \
    tar xzf vsearch-2.3.0-linux-x86_64.tar.gz && \
    mv vsearch-2.3.0-linux-x86_64/bin/vsearch .
RUN rm -rf vsearch-2.3.0-linux-x86_64
RUN rm vsearch-2.3.0-linux-x86_64.tar.gz

WORKDIR /kb/module/dependencies/bin
RUN curl -L http://drive5.com/uchime/uchime4.2.40_src.tar.gz -o uchime4.2.40_src.tar.gz && \
    tar xvf uchime4.2.40_src.tar.gz && \
    cd uchime4.2.40_src && \
    make && \
    cd .. && \
    mv uchime4.2.40_src/uchime  .
RUN rm -rf uchime4.2.40_src
RUN rm uchime4.2.40_src.tar.gz

# -----------------------------------------

COPY ./ /kb/module
RUN mkdir -p /kb/module/work
RUN chmod 777 /kb/module

WORKDIR /kb/module

RUN make all

ENTRYPOINT [ "./scripts/entrypoint.sh" ]

CMD [ ]
