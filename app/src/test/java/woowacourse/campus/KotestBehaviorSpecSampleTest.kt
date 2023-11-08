package woowacourse.campus

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class KotestBehaviorSpecSampleTest : BehaviorSpec({
    Given("날씨가 더운 경우") {
        val samples = List(5) { it }
        When("에어컨을 끄면") {
            val actual = samples.firstOrNull()
            Then("산군이 짜증을 낸다") {
                println("이것은 kotest 샘플이다 이것이야 - !")
                actual shouldNotBe null
                actual.shouldBe(0)
            }
        }
    }
})
