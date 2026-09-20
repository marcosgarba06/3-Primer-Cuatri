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

Para facilitar a interacción con Python, as bibliotecas e a entrega de resultados, as prácticas faranse sobre **[Jupyter Notebooks](https://jupyter.readthedocs.io/en/latest/index.html)**. Para minimizar as diferenzas entre contornos e posíbeis dificultades de configuración (pois interésanos que os resultados sexan repetibles e facilitar a realización das prácticas), os Notebooks executaranse mediante **[Contedores Docker](https://www.docker.com/)**. Deste xeito, é necesario que teñades **[Docker instalado](https://docs.docker.com/desktop/windows/install/)** nos vosos equipos (**isto só hai que facelo unha vez**):

* Descargamos **[Docker Desktop Installer.exe](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe)**
* Executamos `Docker Desktop Installer.exe` (deixar marcado "*Install required Windows components for WSL 2*" ou "*Enable Hyper-V Windows Features*")
* Seguimos as instrucións do asistente e, ao final, seleccionamos "*Cerrar y reiniciar*"
* Ao acender o ordenador de novo, aparecerá un aviso de "*Our Service Agreement has Changed*". Hai que **aceptar** os termos e continuar.
* Se aparece un erro de "*WSL 2 installation is incomplete*", **sen pechar a xanela**, dirixímonos á ligazón que proporciona (**[https://aka.ms/wsl2kernel](https://aka.ms/wsl2kernel)**), descargamos `wsl_update_x64.msi` (ou `wsl_update_arm64.msi` se se usa ARM64), executámolo e seguimos as instrucións. Despois disto, premer en "*Restart*" na xanela "*WSL 2 installation is incomplete*".
* Abrirase unha xanela co texto "*Docker Engine Starting...*". Agardamos ata que apareza "*Get started with Docker in a few easy steps!*" e prememos en "*Skip tutorial*".
* A xanela pasará a amosar "*No containers running*". Xa temos Docker instalado e estamos listos para usalo. Podemos minimizar a xanela de *Docker Desktop*.


**Creación da imaxe** (`build`)

Crearemos a imaxe descrita no ficheiro `Dockerfile`. Para iso, primeiramente debemos descargar e descomprimir os ficheiros que atoparás no Campus Virtual. Unha vez feito isto, abrimos o cartafol contedor, prememos `Shift + clic dereito` nel e seleccionamos "*Abrir a xanela de PowerShell aquí*". Na terminal escribimos (**isto só hai que facelo unha vez**):
```
docker build -t grei_ia .
```
Se aparece un aviso do *Firewall de Windows*, debemos permitir que Docker Desktop Backend se comunique coas redes. Cando remate o comando (levaralle un anaco, pois ten que descargar algunhas cousas), xa teremos a imaxe creada.


**Execución do contedor** (`run`)

O contedor que empregaremos terá como base a imaxe descrita anteriormente. Abriremos o cartafol cos boletíns de prácticas, prememos `Shift + clic dereito` nel e seleccionaremos "*Abrir a xanela de PowerShell aquí*". Na terminal escribimos (**isto haberá que facelo cada vez que queiramos traballar**):
```
docker run -it --rm --name="IntelixenciaArtificial" -v "${PWD}:/home/jovyan/" -p 8888:8888 grei_ia start-notebook.sh --NotebookApp.token=''
```
Pode que apareza un aviso de "*Docker Desktop - Filesharing*", pero podemos ignoralo.

Unha vez executado o comando, poderemos entrar no servidor de Jupyter Notebook a través dun navegador, no enderezo **[http://127.0.0.1:8888](http://127.0.0.1:8888)**.
Finalmente, para pechar o contedor (**asegúrate antes de ter gardados os cambios nos Notebooks**) prememos `Control-C` na terminal na que executamos o comando.

</div>
