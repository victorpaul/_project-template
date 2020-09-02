package com.projecttemplategroup.projecttemplateartifact.utils

import com.projecttemplategroup.projecttemplateartifact.BaseSpringTest
import org.springframework.beans.factory.annotation.Autowired

import scala.jdk.CollectionConverters._


class JavaUtil_SpringTest extends BaseSpringTest {

  @Autowired
  var javaUtil: JavaUtil = _

  Feature("check method getArray") {

    Scenario("success get result") {
      
      javaUtil.getArray(3).asScala shouldBe Seq(0, 1, 2)

    }

  }

}
