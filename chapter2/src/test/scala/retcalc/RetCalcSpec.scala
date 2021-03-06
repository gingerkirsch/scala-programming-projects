package retcalc
import org.scalactic.{Equality, TolerantNumerics, TypeCheckedTripleEquals}
import org.scalatest.{Matchers, WordSpec}
class RetCalcSpec extends WordSpec with Matchers with
  TypeCheckedTripleEquals {

  implicit val doubleEquality: Equality[Double] =
    TolerantNumerics.tolerantDoubleEquality(0.0001)

  "RetCalc.futureCapital" should {
    "calculate the amount of savings I will have in n months" in {
      val actual = RetCalc.futureCapital(
        interestRate = 0.04 / 12, nbOfMonths = 25 * 12,
        netIncome = 3000, currentExpenses = 2000,
        initialCapital = 10000)
      val expected = 541267.1990
      actual should ===(expected)
    }

    "calculate how much savings will be left after having taken a pension for n months" in {
      val actual = RetCalc.futureCapital(
      interestRate = 0.04/12, nbOfMonths = 40 * 12,
      netIncome = 0, currentExpenses = 2000, initialCapital =
      541267.1990)
      val expected = 309867.53176
      actual should ===(expected)
    }
  }

  "RetCalc.simulatePlan" should {
    "calculate the capital at retirement and the capital after death" in {
      val (capitalAtRetirement, capitalAfterDeath) =
        RetCalc.simulatePlan(
          interestRate = 0.04 / 12,
          nbOfMonthsSaving = 25 * 12, nbOfMonthsInRetirement = 40 * 12,
          netIncome = 3000, currentExpenses = 2000,
          initialCapital = 10000)
      capitalAtRetirement should === (541267.1990)
      capitalAfterDeath should === (309867.5316)
    }
  }
}