package com.projecttemplategroup.projecttemplateartifact.utils

import com.projecttemplategroup.projecttemplateartifact.BaseFastTest

import scala.jdk.CollectionConverters._


class JavaUtil_FastTest extends BaseFastTest {

  val javaUtil: JavaUtil = new JavaUtilImpl()

  Feature("check method getArray") {

    Scenario("success get result") {

      javaUtil.getArray(3).asScala shouldBe Seq(0, 1, 2)

    }

  }

}
