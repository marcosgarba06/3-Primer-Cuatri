(deftemplate Persona
    (field Nombre)
    (field Edad)
    (field Sexo)
    (field EstadoCivil)
)

(deffacts VariosHechos
    (Persona
        (Nombre Juan)
        (Edad 30)
        (EstadoCivil casado)
        (Sexo V)
    )
)