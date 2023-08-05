# Peticiones para cada entidad

## Patient

**POST**

```
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

```
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

```
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

```
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

```
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

```
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

```
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

```
{
  "patientId": 2,
  "prognosticDate": "2023-08-02",
  "patientDescription": "Patient is suffering from a mild flu and needs rest."
}
```

## Physical Exploration

**POST**

```
{
  "patientId": 3,
  "explorationDescription": "Patient underwent physical examination.",
  "explorationDate": "2023-08-02T12:34:56"
}
```
