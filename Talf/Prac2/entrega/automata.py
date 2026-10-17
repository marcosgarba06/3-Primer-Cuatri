#!/usr/bin/env python3
# Codigo de Marcos García Blanco
import sys, argparse

class Automata:
    '''Clase que representa un autómata'''
    def __init__(self):
        self.estados = [] # lista de nombres de los estados (ej MaqExp: q00, q05...q35,qaa...qdd)
        self.alfabeto = [] # lista de simbolos de entrada (ej MaqExp: 5, 1, 2, a, b, c, d)
        self.inicial = None # el estado inicial empieza en None hasta que se lea el fichero (es el primer estaado que aparece)
        self.finales = set() # estados finales (como son fijos y no importa el orden es set)
        self.transiciones = {} # diccionario de transiciones
            # la clave es una tupla (estado, símbolo) y el valor es un set con los estados a los q se va
            # la epsilon usa el símbolo "" y una casilla vacía es set()
            # (ej MaqExp: {('q00', '5'): {'q05'}, ('q05', '1'): {'q15'}, ..., ('q35', 'd'): {'qdd', 'q00'}})

    def _leerListaNumero(self, linea, nombre):
        '''Método privado: lee una línea tipo "#N a b c...", comprueba N y devuelve la lista [a, b, c...]'''
        
        partes = linea.lstrip("#").split() # quita el # y separa por espacios
        nombres = partes[1:] # descarta el número

        if len(nombres) != int(partes[0]): # comprueba que el número indicado coincide
            print("Error: el número de", nombre, "no coincide con los leídos")
            sys.exit(1)
        return nombres


    def cargarFichero(self, nFichero):
        '''Lee la definición del autómata desde un fichero con el formato de la práctica'''
        try:
            arch = open(nFichero, 'r', encoding='utf-8') # abrir archivo en modo lectura
        except FileNotFoundError:
            print("Error: No se pudo abrir el fichero", nFichero)
            sys.exit(1)

        lineas = [] # lista de lineas del fichero por orden
        for linea in arch: # recorre cada linea del archivo
            linea = linea.strip() # quitar espacios al principio y final
            if len(linea) > 0 and linea != "":
                lineas.append(linea) # añadir linea a la lista de lineas

        arch.close() # cerrar archivo
        if len(lineas) < 4: # comprobar que hay al menos 4 lineas (estados, finales, simbolos, tabla)
            print("Error: el fichero no tiene el formato esperado")
            sys.exit(1)

        self.estados = self._leerListaNumero(lineas[0], "estados") # primera linea son los estados
        self.inicial = self.estados[0] # el primer estado es el inicial
        self.finales = set(self._leerListaNumero(lineas[1], "estados finales")) # se crea set de la lista para los finales
        self.alfabeto = self._leerListaNumero(lineas[2], "símbolos") #tercera linea simbolos del alfabeto
        
        for f in self.finales: #recorre los estados finales y comprueba que existen
            if f not in self.estados:
                print("Error: el estado final", f, "no está en la lista de estados")
                sys.exit(1)

        # ignorar la cuarta linea (titulo de la tabka)

        # ahora se leen las filas de la tabla de transiciones
        filas = lineas[4:] # descartar las 4 primeras lineas del archivo (ya se han procesado)
        simbolos = self.alfabeto + [""] # una columna más para la epsilo que usa ""

        if len(filas) != len(self.estados): # una fila por estado
            print("Error: el número de filas de la tabla no coincide con el número de estados")
            sys.exit(1)

        for i in range(len(filas)): # recorrer cada fila de la tabla
            estado = self.estados[i] # el estado de la fila
            columnas = filas[i].split("#")[:-1] # trozos entre #, sin el último vacío

            if len(columnas) != len(simbolos): # una columna por símbolo + la epsilon
                print("Error: la fila del estado", estado, "tiene", len(columnas), "columnas y debería tener", len(simbolos))
                sys.exit(1)

            for j in range(len(columnas)): # recorrer cada columna de la fila
                destinos = set(columnas[j].split()) # destinos de esta casilla
                
                for d in destinos: # comprobar que cada destino es un estado definido
                    if d not in self.estados: # se mira si esta en los estados
                        print("Error: la fila del estado", estado, "tiene un destino desconocido:", d)
                        sys.exit(1)
                self.transiciones[(estado, simbolos[j])] = destinos # guardar la casilla en el diccionario de transiciones


    def _clausura_e(self, estado):
        '''devuelve un set de estados alcanzables desde "estado" solo con transiciones epsilon'''
        clausura = {estado} # el estado siempre esta en su clausura
        pendientes = [estado] # estados que no se han explorado aun 

        while len(pendientes) > 0:
            actual = pendientes.pop() # saca el último de la lista
            for destino in self.transiciones[(actual, "")]: #por cada transicion vacia desde el est act
                if destino not in clausura: # si aun no se ha explorado
                    clausura.add(destino) # se añade a la clausura
                    pendientes.append(destino) # se mete en pendientes para ver si hay mas
        return clausura

    def simularAFN(self, cad):
        '''Simula el autómata como AFN, mostrando los estados paso a paso. Devuelve True si acepta'''
        estActuales = self._clausura_e(self.inicial) # estados actuales empieza con la clausura del inicial
        print("Inicio:", sorted(estActuales)) # mostrar los estados iniciales

        for simbolo in cad: 
            if simbolo not in self.alfabeto:# si el simbolo no esta en el alfabeto duro 
                print("Error: símbolo", simbolo, "no pertenece al alfabeto")
                return False
            
            siguientes = set() # estados a los que se puede ir con el simbolo actual

            for estado in estActuales: # por cada estado actual
                for destino in self.transiciones[(estado, simbolo)]: # por cada estado al que se puede ir con el simbolo
                    siguientes.update(self._clausura_e(destino)) # añadir la clausura de cada destino a los siguientes

            estActuales = siguientes # los estados actuales son los siguientes
            print(simbolo, "->", sorted(estActuales)) # mostrar los estados actuales
        
        if len(estActuales & self.finales) > 0: # hay algún estado final entre los actuales
            print("Cadena aceptada")
            return True
        else:
            print("Cadena rechazada")
            return False

    def esDeterminista(self):
        ''' True si es determinista, False si no lo es'''
        # es determinista si no hay epsilon (estan vacias) y no hay 2 transiciones desde 
        # el mismo estado con el mismio simbolo
        for estado in self.estados:
            if len(self.transiciones[(estado,"")]) > 0: # hay transicion epsilon
                return False
            else: 
                for simbolo in self.alfabeto:
                    if len(self.transiciones[(estado,simbolo)]) > 1: # hay mas de un destino con el mismo simbolo
                        return False
        return True # si no se detecta ningun casi de indeterminismo devuelcve True

    def simularAFD(self, cad):
        '''Simula el automata como AFD, mostrando los estados paso a paso. Devuelve True si acepta'''
        if not self.esDeterminista(): # si no es determinista
            print("Error: el autómata no es determinista")
            return False

        estActual = self.inicial # el estado actual empieza con el inicial
        print("Inicio:", estActual) # mostrar el estado inicial
        
        for simbolo in cad:
            if simbolo not in self.alfabeto: # comprobar que el simbolo pertenece al alfabeto
                print("Error: símbolo", simbolo, "no pertenece al alfabeto")
                return False

            destino = self.transiciones[(estActual, simbolo)] # conjunto de estados a los que se puede ir con el simbolo actual
            if len(destino) == 0: # si la casilla está vacía, se bloquea y rechaza
                print(simbolo, "-> [] (Autómata bloqueado)")
                print("Cadena rechazada")
                return False
            
            estActual = next(iter(destino)) # como es determinista, solo hay un destino, se coge el primero
            #next(iter(...)) devuelve el primer elemento del conjunto, que es el único en este caso
            print(simbolo, "->", estActual) # mostrar el estado actual

        if estActual in self.finales: # el estado actual es final
            print("Cadena aceptada")
            return True
        else:   
            print("Cadena rechazada")
            return False

def main():
    parseador = argparse.ArgumentParser(description="Simulador de autómatas finitos (AFD y AFN)") # definir el parseador de argumentos
    parseador.add_argument("fichero", help="fichero de texto con la definición del autómata") # argumento 1 (fichero) obligatorio
    parseador.add_argument("cadena", nargs="?", default=None, help="cadena a simular, si se omite, se pide símbolo a símbolo")
    # argumento 2 (cadena) opcional
    #nargs="?" significa que es opcional, default=None significa que si no se da, vale None

    args = parseador.parse_args() # parsear los argumentos de la línea de comandos

    aut = Automata() # crear objeto autómata
    aut.cargarFichero(args.fichero) # cargar el autómata desde el fichero
    print("Autómata cargado desde", args.fichero)

    esAFD = aut.esDeterminista() # se comprueba si es determinista o no
    if esAFD:
        print("El autómata es determinista (AFD)")
    else:
        print("El autómata es no determinista (AFN)")

    if args.cadena is not None: # si se ha dado una cadena por argumento
        print("Simulando la cadena:", args.cadena)
        cadena = args.cadena.replace(" ", "") # los espacios son pasos epsilon y no consumen símbolo
        if esAFD:
            aut.simularAFD(cadena) # simular como AFD
        else:
            aut.simularAFN(cadena) # simular como AFN
    else: # si no se ha dado una cadena por argumento, se pide por teclado
        cad = ""
        while True:
            simbolo = input("Símbolo o cadena (!l para limpiar, !q para terminar): ")
            if simbolo == "!q": # salir
                break
            elif simbolo == "!l": # limpiar cadena de entrada, para poder probar con otra
                cad = ""
                print("Cadena de entrada limpiada")
            else:
                simbolo = simbolo.replace(" ", "") # quitar espacios en blanco
                if simbolo == "": # Enter: paso epsilon, la cadena no cambia
                    print("Paso epsilon: no se consume ningún símbolo")
                else:
                    # comprobamos que cada caracter introducido pertenezca al alfabeto
                    invalido = False
                    for c in simbolo:
                        if c not in aut.alfabeto:  # símbolo inválido
                            print("Error: símbolo", c, "no pertenece al alfabeto")
                            invalido = True
                            break
                    if invalido:
                        continue # no se simula, se vuelve a pedir
                    
                    cad = cad + simbolo # los símbolos válidos se añaden a la cadena

            print("Cadena actual:", repr(cad)) 
            if esAFD: # dependiendo del tipo se simula
                aut.simularAFD(cad)
            else:
                aut.simularAFN(cad)

if __name__ == "__main__":
    main()
