# Intelixencia Artificial

## Introdución

<div style="text-align: justify">
A realización das prácticas correspondentes a esta materia levarase a cabo empregando Python xunto co uso de distintas bibliotecas:

* **[aima-python](https://github.com/aimacode/aima-python)**, código en Python do libro Artificial Intelligence: A Modern Approach, empregado como bibliografía básica da materia.
* **[NumPy]()**
* **[SciPy]()**
* **[Pandas]()**
* **[Matplotlib]()**
* **[Scikit-Learn](https://scikit-learn.org)**, unha biblioteca de aprendizaxe automática (Machine Learning) en Python.


## Configuración do contorno de prácticas

Para facilitar a interacción con Python, as bibliotecas e a entrega de resultados, as prácticas faranse sobre **[Jupyter Notebooks](https://jupyter.readthedocs.io/en/latest/index.html)**. Para minimizar as diferenzas entre contornos e as posibles dificultades de configuración (pois interésanos que os resultados sexan repetibles e facilitar a realización das prácticas), os Notebooks executaranse mediante **[Contedores Docker](https://www.docker.com/)**. Deste xeito, é necesario que teñades **[Docker instalado](https://docs.docker.com/get-docker/)** nos vosos equipos. 


**Creación da imaxe** (`build_image.sh`)

Crearemos unha imaxe que usará como base `jupyter/scipy-notebook:hub-4.0.2` (sería bo descargala canto antes mediante: `docker pull jupyter/scipy-notebook:hub-4.0.2`). A esta engadirémoslle unha serie de dependencias, descritas no ficheiro `Dockerfile`. A creación da imaxe en Linux (ou WSL) realízase co seguinte comando (incluído en `build_image.sh`):
```
docker build -t grei_ia_2025 .
```


**Execución do contedor** (`run_container.sh`)

O contedor que empregaremos terá como base a imaxe descrita anteriormente. Ao executar o contedor, indicaremos que mapee o porto do servidor de Jupyter Notebook (porto `8888`) a un porto do noso ordenador (o `8888`, por exemplo). Tamén lle indicaremos que monte o noso directorio actual (`${PWD}`) no canto do directorio home do contedor (`/home/jovyan/`); deste xeito o servidor poderá ver os nosos ficheiros e editalos sen ningún problema. Para solucionar posíbeis problemas de permisos, indicámoslle ao contedor que o usuario teña o mesmo GID e UID ca o do noso PC (`--user root -e NB_UID=$(id -u) -e NB_GID=$(id -g) -e CHOWN_HOME=yes -e CHOWN_HOME_OPTS='-R'`). Adicionalmente, faremos que non sexa necesario autenticarnos á hora de conectarnos ao servidor (`--NotebookApp.token=''`), para evitar posíbeis problemas con tokens e sesións. Por último, interésanos executar o contedor interactivamente (`-it`) e que este se elimine unha vez pechado (`--rm`), para minimizar conflitos. Todo isto en Linux realizaríase co seguinte comando (incluído en `run_container.sh`):
```
docker run --user root -e NB_UID=$(id -u) -e NB_GID=$(id -g) \
-e CHOWN_HOME=yes -e CHOWN_HOME_OPTS='-R' -it --rm --name="IntelixenciaArtificial" \
-v "${PWD}":/home/jovyan/ -p 8888:8888 grei_ia_2025 start-notebook.sh --NotebookApp.token=''
```

Unha vez executado o comando, poderemos acceder ao servidor de Jupyter Notebook a través dun navegador, no enderezo **[http://127.0.0.1:8888](http://127.0.0.1:8888)**.

Finalmente, para pechar o contedor (**asegúrate antes de ter gardados os cambios nos Notebooks**) prememos `Control-C` na terminal na que executamos o comando.

</div>
