FROM jupyter/scipy-notebook:hub-4.0.2

USER root

RUN apt-get update

USER ${NB_UID}

RUN pip install pyppeteer
RUN pyppeteer-install

RUN conda install -y -q -c conda-forge pydot python-graphviz IPython pygments

WORKDIR "${HOME}"
