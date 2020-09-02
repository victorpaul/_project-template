package com.projecttemplategroup.projecttemplateartifact

import org.junit.runner.RunWith
import org.scalamock.scalatest.MockFactory
import org.scalatest.GivenWhenThen
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
abstract class BaseFastTest extends AnyFeatureSpec with Matchers with GivenWhenThen with MockFactory{

}
