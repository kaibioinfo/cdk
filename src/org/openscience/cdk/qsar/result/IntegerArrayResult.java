/* $RCSfile$
 * $Author$
 * $Date$
 * $Revision$
 * 
 * Copyright (C) 2004-2005  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.openscience.cdk.qsar.result;

import java.util.ArrayList;
import java.util.List;

/**
 * @cdk.module qsar
 */
public class IntegerArrayResult implements DescriptorResult {
    
    private List array;
    
    public IntegerArrayResult() {
        this.array = new ArrayList();
    }
    
    public IntegerArrayResult(int size) {
        this.array = new ArrayList(size);
    }
    
    public void add(int value) {
        array.add(new Integer(value));
    }
    
    /**
     * The first int is at index = 0;
     */
    public double get(int index) {
        return ((Integer)this.array.get(index)).intValue();
    }
    
    public int size() {
        return this.array.size();
    }

}

