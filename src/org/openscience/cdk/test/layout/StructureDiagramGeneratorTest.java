/*
 *  $RCSfile$
 *  $Author$
 *  $Date$
 *  $Revision$
 *
 *  Copyright (C) 1997-2003  The Chemistry Development Kit (CDK) project
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.openscience.cdk.test.layout;

import org.openscience.cdk.controller.*;
import org.openscience.cdk.*;
import org.openscience.cdk.io.*;
import org.openscience.cdk.layout.*;
import org.openscience.cdk.renderer.*;
import org.openscience.cdk.smiles.*;
import org.openscience.cdk.templates.*;
import java.util.*;
import java.io.*;
import java.net.URL;
import javax.vecmath.Vector2d;
import javax.vecmath.Point2d;
import junit.framework.*;

/**
 *  Description of the Class
 *
 *@author     steinbeck
 *@created    August 29, 2003
 */
public class StructureDiagramGeneratorTest extends TestCase
{

	MoleculeListViewer moleculeListViewer = null;


	/**
	 *  Constructor for the StructureDiagramGeneratorTest object
	 *
	 *@param  name  Description of the Parameter
	 */
	public StructureDiagramGeneratorTest(String name)
	{
		super(name);
	}


	/**
	 *  The JUnit setup method
	 */
	public void setUp() { }


	/**
	 *  A unit test suite for JUnit
	 *
	 *@return    The test suite
	 */
	public static Test suite()
	{
		return new TestSuite(StructureDiagramGeneratorTest.class);
	}


	/**
	 *  Description of the Method
	 */
	public void runVisualTests()
	{
		moleculeListViewer = new MoleculeListViewer();
		showIt(MoleculeFactory.loadMolecule("data/mdl/reserpine.mol"), "Reserpine");
		showIt(MoleculeFactory.loadMolecule("data/mdl/four-ring-5x10.mol"), "5x10 condensed four membered rings");
		showIt(MoleculeFactory.loadMolecule("data/mdl/six-ring-4x4.mol"), "4x4 condensed six membered rings");
		showIt(MoleculeFactory.loadMolecule("data/mdl/polycarpol.mol"), "Polycarpol");
		showIt(MoleculeFactory.makeAlphaPinene(), "alpha-Pinene");
		showIt(MoleculeFactory.makeBiphenyl(), "Biphenyl");
		showIt(MoleculeFactory.make4x3CondensedRings(), "4x3CondensedRings");
		showIt(MoleculeFactory.makePhenylEthylBenzene(), "PhenylEthylBenzene");
		showIt(MoleculeFactory.makeSpiroRings(), "Spiro");
		showIt(MoleculeFactory.makeMethylDecaline(), "Methyldecaline");
		showIt(MoleculeFactory.makeBranchedAliphatic(), "Branched aliphatic");
		showIt(MoleculeFactory.makeDiamantane(), "Diamantane - A Problem! - Solve it! :-)");
		showIt(MoleculeFactory.makeEthylCyclohexane(), "Ethylcyclohexane");
		showIt(MoleculeFactory.makeBicycloRings(), "Bicyclo-[2.2.2]-octane");
	}


	/**
	 *  Description of the Method
	 *
	 *@param  molecule  Description of the Parameter
	 *@param  name      Description of the Parameter
	 *@return           Description of the Return Value
	 */
	private boolean showIt(Molecule molecule, String name)
	{
		MoleculeViewer2D mv = new MoleculeViewer2D();
		try
		{
			mv.setAtomContainer(generateCoordinates(molecule));
			moleculeListViewer.addStructure(mv, name);
		} catch (Exception exc)
		{
			System.out.println("*** Exit due to an unexpected error during coordinate generation ***");
			exc.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 *  Description of the Method
	 *
	 *@param  m              Description of the Parameter
	 *@return                Description of the Return Value
	 *@exception  Exception  Description of the Exception
	 */
	public AtomContainer generateCoordinates(Molecule m) throws Exception
	{
		StructureDiagramGenerator sdg = new StructureDiagramGenerator();
		sdg.setMolecule(m);
		sdg.generateCoordinates(new Vector2d(0, 1));
		return sdg.getMolecule();
	}


	/**
	 *  The main program for the StructureDiagramGeneratorTest class
	 *
	 *@param  args  The command line arguments
	 */
	public static void main(String[] args)
	{
		try
		{
			StructureDiagramGeneratorTest sdg = new StructureDiagramGeneratorTest("StructureDiagramGeneratorTest");
			//sdg.runVisualTests();
			sdg.bug736137();
		} catch (Exception exc)
		{
			exc.printStackTrace();
		}
	}


	/**
	 *  Description of the Method
	 *
	 *@exception  java.lang.Exception  Description of the Exception
	 */
	public void bug736137() throws java.lang.Exception
	{
		String filename = "data/mdl/bug736137.mol";
		InputStream ins = this.getClass().getClassLoader().getResourceAsStream(filename);
		MDLReader reader = new MDLReader(new InputStreamReader(ins));
		Molecule molecule = (Molecule) reader.read((ChemObject) new Molecule());
		MoleculeViewer2D.display(molecule, true);

	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testAlphaPinene() throws Exception
	{
		Molecule m = MoleculeFactory.makeAlphaPinene();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testBiphenyl() throws Exception
	{
		Molecule m = MoleculeFactory.makeBiphenyl();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void test4x3CondensedRings() throws Exception
	{
		Molecule m = MoleculeFactory.make4x3CondensedRings();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testPhenylEthylBenzene() throws Exception
	{
		Molecule m = MoleculeFactory.makePhenylEthylBenzene();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testSpiroRings() throws Exception
	{
		Molecule m = MoleculeFactory.makeSpiroRings();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testMethylDecaline() throws Exception
	{
		Molecule m = MoleculeFactory.makeMethylDecaline();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testBranchedAliphatic() throws Exception
	{
		Molecule m = MoleculeFactory.makeBranchedAliphatic();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testDiamantane() throws Exception
	{
		Molecule m = MoleculeFactory.makeDiamantane();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testEthylCyclohexane() throws Exception
	{
		Molecule m = MoleculeFactory.makeEthylCyclohexane();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testBicycloRings() throws Exception
	{
		Molecule m = MoleculeFactory.makeBicycloRings();
		AtomContainer ac = generateCoordinates(m);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testBenzene() throws Exception
	{
		SmilesParser sp = new SmilesParser();
		Molecule mol = sp.parseSmiles("c1ccccc1");
		AtomContainer ac = generateCoordinates(mol);
	}


	/**
	 *  A unit test for JUnit
	 *
	 *@exception  Exception  Description of the Exception
	 */
	public void testBug780545() throws Exception
	{
		Molecule mol = new Molecule();
		mol.addAtom(new Atom("C"));
		AtomContainer ac = generateCoordinates(mol);
	}
}

