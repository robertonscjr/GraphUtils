package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.graph.GraphTest;
import test.structure.components.StructureTests;

@RunWith(Suite.class)
@SuiteClasses({ GraphTest.class, StructureTests.class})
public class AllTestsSuite {

}
