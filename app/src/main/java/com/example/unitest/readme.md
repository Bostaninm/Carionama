### Assumptions

There is two Json file name "AdultIndicators" and "TeenIndicators" that has structure of
`[
    {
        "id": "indicatorId1",
        "name": "indicator name1",
        "description": "Description of indicator 1 that will be shown to user",
        "options": {
            "Option 1(will be shown as selectable to user)": 0.0,
            "Option 2": 1.234,
            ...
            "Option n": 2.919,
        }
    },
    ...
    {
        "id": "indicatorId2",
        "name": "indicator name2",
        "description": "Description of indicator 2 that will be shown to user",
        "options": {
            "Option 1": 0.0,
            "Option 2": 2.4,
            ...
            "Option n": 4.919,
        }
    },
]`

If there is a age group indicator then:
1. it's id should be name "AgeGroup"  
2. it's options name should be name by it's lower bound and be castable to integer
3. options should be sorted (ascending)
`
{
    "id": "AgeGroup",
    "name": "Age Group",
    "description": "Please select your age group.",
    "options": {
        "35": 0.0,    35-44 years
        "45": 4.071,  45-54 years
        "55": 8.572,  55-64 years
        "65": 11.311  65+ years
    }
},
`
BMI indicator should be like below (value of name, description and name of options could be
change) :
`
{
    "id": "BMI",
    "name": "Body Mass Index (BMI)",
    "description": "How do you assess your Body Mass Index (BMI)?",
    "options": {
        "Lower than Normal (Underweight)": 1.0,
        "Normal or Higher than Normal": 0.0
    }
},
`
