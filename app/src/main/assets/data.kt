import com.example.unitest.Indicator
import com.example.unitest.IndicatorOption

val oldIndicators = listOf<Indicator>(
    Indicator("factor.SES", "Description about SES", listOf (
        IndicatorOption("Quantile 1", -2f),
        IndicatorOption("Quantile 2", -1f),
        IndicatorOption("Quantile 3", 0f),
        IndicatorOption("Quantile 4", 1f),
        IndicatorOption("Quantile 5", 2f),
    )),
    Indicator("factor.EducationLevel", "Education level", listOf (
        IndicatorOption("Lower Education", 0f),
        IndicatorOption("Higher Education", 10F)
    )),
    Indicator("factor.FrequentToothBrushing", "Frequent tooth brushing", listOf (
        IndicatorOption("Frequent", 1f),
        IndicatorOption("Infrequent", 2F)
    )),
    Indicator("factor.Flossing", "Flossing", listOf (
        IndicatorOption("Flossing", 1f),
        IndicatorOption("Not Flossing", 2F)
    )),
    Indicator("factor.Mouthwash", "Mouthwash", listOf (
        IndicatorOption("Using", 1f),
        IndicatorOption("Not Using", 2F)
    )),
    Indicator("factor.Gender", "Mouthwash", listOf (
        IndicatorOption("Male", 1f),
        IndicatorOption("Female", 2F)
    )),
    Indicator("factor.Age", "Age", listOf (
        IndicatorOption("18-34", 1f),
        IndicatorOption("35-44", 2f),
        IndicatorOption("45-54", 3F),
        IndicatorOption("55-64", 4F),
        IndicatorOption("65+", 5F),
    )),
    Indicator("factor.MaritalStatus", "Marital Status", listOf (
        IndicatorOption("Single", 10f),
        IndicatorOption("Married", 3F),
        IndicatorOption("Divorced or Widowed", 0F),
    )),
    Indicator("factor.BMI", "BMI", listOf (
        IndicatorOption("Lower", 1f),
        IndicatorOption("Normal/High", 2F),
    )),
    Indicator("factor.Smoking", "Somking", listOf (
        IndicatorOption("Smoker", 1f),
        IndicatorOption("non-Smoker", 2F),
    )),
    Indicator("factor.Alcohol", "Alcohol Use", listOf (
        IndicatorOption("Using", 1f),
        IndicatorOption("not Using", 2F),
    )),
    Indicator("factor.HTN", "Hypertension", listOf (
        IndicatorOption("Hypertensive", 1f),
        IndicatorOption("non-Hypertensive", 2F),
    )),
)

val youngIndicators = listOf<Indicator>(
    Indicator("factor.genetic", "Genetic Factor blah blah blah", listOf (
        IndicatorOption("option a", 1f),
        IndicatorOption("option b", 2F)
    )),
    Indicator("factor.environment", "environment Factor blah blah blah", listOf (
        IndicatorOption("option a", 1f),
        IndicatorOption("option b", 2F)
    )),
    Indicator("factor.alcohol", "alcohol Factor blah blah blah", listOf (
        IndicatorOption("option a", 1f),
        IndicatorOption("option b", 2F)
    )),
)
