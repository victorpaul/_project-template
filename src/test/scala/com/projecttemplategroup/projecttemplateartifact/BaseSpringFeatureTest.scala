package com.projecttemplategroup.projecttemplateartifact

import org.scalatest.featurespec.AnyFeatureSpecLike
import org.scalatest.matchers.should.Matchers
import org.springframework.test.context.TestContextManager

abstract class BaseSpringFeatureTest extends BaseSpringTest with AnyFeatureSpecLike with Matchers {

  // required for proper injecting in scala
  new TestContextManager(this.getClass).prepareTestInstance(this)

}
