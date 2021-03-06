package org.encog.ca.universe.basic;

import java.io.Serializable;

import org.encog.ca.CellularAutomataError;
import org.encog.ca.universe.DiscreteCell;
import org.encog.ca.universe.UniverseCell;
import org.encog.mathutil.randomize.RangeRandomizer;
import org.encog.util.EngineArray;
import org.encog.util.Format;

public class BasicDiscreteCell implements DiscreteCell, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] data;
	private int elementCount;
	
	public BasicDiscreteCell(int theSize, int theElementCount) {
		this.data = new int[theSize];
		this.elementCount = theElementCount;
	}
	
	public double get(int index) {
		return this.data[index];
	}

	@Override
	public void randomize() {
		for(int i=0;i<this.data.length;i++) {
			this.data[i] = RangeRandomizer.randomInt(0, elementCount);
		}
		
	}

	@Override
	public void copy(UniverseCell sourceCell) {
		if( !(sourceCell instanceof BasicDiscreteCell ) ) {
			throw new CellularAutomataError("Can only copy another BasicDiscreteCell.");
		}
		
		for(int i=0;i<this.data.length;i++) {
			this.data[i] = (int)sourceCell.get(i);
		}		
		
	}

	@Override
	public double getAvg() {
		return (int)EngineArray.mean(this.data);
	}	
	
	@Override
	public void set(int i, double d) {
		this.data[i] = (int)d;		
	}

	@Override
	public int size() {
		return this.data.length;
	}

	@Override
	public void set(int idx, double[] d) {
		for(int i=0;i<this.data.length;i++) {
			this.data[i]=(int)d[idx+i];
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("'[");
		result.append(this.getClass().getSimpleName());
		result.append(":");
		for(int i=0;i<this.size();i++) {
			if( i>0 ) {
				result.append(',');
			}
			result.append(i);
			result.append("=");
			result.append(this.data[i]);
		}
		result.append("]");
		return result.toString();
	}
}
