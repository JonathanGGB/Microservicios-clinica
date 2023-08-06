# Peticiones para crear cada entidad
(En el caso de querer actualizar un registro, solo es necesario añadir la clave "id" y el valor entero correspondiente además de cambiar el tipo de petición. Para el get y el delete, se deben usar los métodos correspondientes. El caso del delete, siempre es con el id.) 

## Patient

**POST**

```JSON
{
  "name": "John",
  "lastnames": "Doe",
  "birthday": "1990-01-01",
  "sex": false,
  "address": "123 Main St",
  "cellphoneNum": "+1234567890",
  "email": "john.doe@example.com",
  "maritalStatus": "Married",
  "schooling": "Graduate",
  "occupation": "Engineer"
}
```

## Family History

**POST**

```JSON
{
  "heartDiseases": true,
  "allergies": false,
  "diabetes": true,
  "psychiatrics": false,
  "otherSyndromes": null,
  "patientId": 2
}
```

## Non-pathological personal history

**POST**

```JSON
{
  "personalHabits": "Avoids fast food and eats fruits regularly.",
  "bathroom": "Uses bathroom twice a day.",
  "room": "Sleeps in a well-ventilated room.",
  "smoking": "Non-smoker.",
  "alcoholism": "Occasional drinker.",
  "vaccines": "Up to date with required vaccinations.",
  "physicalActivity": "Engages in regular exercise.",
  "feeding": "Balanced diet with adequate protein and vegetables.",
  "civilStatus": "Married",
  "patientId": 2
}
```

## Obstetric-Gynecologist History.

**POST**

```JSON
{
  "menarche": "2008-05-15",
  "mensualCicle": 28,
  "basl": 98,
  "birthNum": 2,
  "lpd": "2023-07-30",
  "abortions": 1,
  "cesareans": 0,
  "patientId": 3
}
```

## Pathological Personal History

**POST**

```JSON
{
  "surgeries": "Appendectomy, tonsillectomy",
  "addictions": "None",
  "std": "Negative",
  "traumas": "Fractured arm in 2010",
  "allergies": "Pollen",
  "jointAliments": "Arthritis",
  "patientId": 2
}
```

## Perinatal History

**POST**

```JSON
{
  "patientId": 3,
  "gestationWeeks": 38,
  "apgar": "8 at 1 minute, 9 at 5 minutes",
  "weigh": 3.2,
  "heigth": 50.5,
  "pregnancyNum": 2,
  "birthProblems": "None"
}
```

## Current Suferrings

**POST**

```JSON
{
  "patientId": 2,
  "date": "2023-08-02T12:34:56",
  "description": "Patient complains of headache and fever.",
  "evolution": "Prescribed antibiotics and rest.",
  "state": "In Progress"
}
```

## Prognostics

**POST**

```JSON
{
  "patientId": 2,
  "prognosticDate": "2023-08-02",
  "patientDescription": "Patient is suffering from a mild flu and needs rest."
}
```

## Physical Exploration

**POST**

```JSON
{
  "patientId": 3,
  "explorationDescription": "Patient underwent physical examination.",
  "explorationDate": "2023-08-02T12:34:56"
}
```
##  Human System

**POST**

```JSON
{
  "humanSystemName": "Motor"
}
```

##  Diagnosis

**POST**

```JSON
{
  "diagnosis": "El paciente cuenta con Neuritis óptica",
  "humanSystemId": 1
}
```

##  Reassessment

**POST**

```JSON
{
  "reassessment": "Cambios positivos en la actividad muscular",
  "humanSystemId": 4
}
```

## System Disease

**POST**
Para este caso, será necesario pasar como parámetro en la ruta el ID del respectivo *human system*.
> Path: http://localhost:{port}/studies/system_disease/{humanSystemId}

```JSON
{
  "diseaseName": "Pares craneales"
}

```

## Medical History

**POST**

```JSON
{
    "patientId": 3,
    "date": "2023-08-05",
    "diagnosisId": 2,
    "treatments": "Se realizará tratamiento de la causa subyacente, en este caso, se detectó infección bacteriana."
}
```

## Complete Medical History

**GET**

Para obtener todos los registros relacionados a un paciente y un historial médico en específico, será necesario pasar como parámetro en la ruta el ID del paciente (*patient*) y el ID del historial médico (*medical history*).
> Path: http://localhost:{port}/studies/complete-medical-history/{patientId}/{medicalHistoryId}

#### Ejemplo de respuesta:
```JSON
{
    "diagnosisDto": {
        "diagnosis": "El paciente cuenta con Neuritis óptica",
        "diagnosisDate": "2023-08-06T04:23:28.579+00:00"
    },
    "humanSystemDto": {
        "humanSystemName": "Nervioso",
        "diseases": [
            {
                "diseaseName": "Pares craneales"
            },
            {
                "diseaseName": "Dermatomas"
            }
        ]
    },
    "medicalHistoryDto": {
        "date": "2023-08-05T00:00:00.000+00:00",
        "treatments": "Se realizará tratamiento de la causa subyacente, en este caso, se detectó infección bacteriana."
    }
}
```
