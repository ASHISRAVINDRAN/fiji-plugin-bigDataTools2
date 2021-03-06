/*-
 * #%L
 * Software for the reconstruction of multi-view microscopic acquisitions
 * like Selective Plane Illumination Microscopy (SPIM) Data.
 * %%
 * Copyright (C) 2012 - 2017 Multiview Reconstruction developers.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
package de.embl.cba.bigDataTools2.boundingBox;

import net.imglib2.Interval;
import net.imglib2.Positionable;
import net.imglib2.RealInterval;
import net.imglib2.RealPositionable;
import net.imglib2.util.Util;
// TODO: check if this class is needed at all. Estimate might be taken from FileInfoSource nX,nY,nZ! -- ashis

/**
 * Class taken from MultiView Reconstruction code base by Stephan Preibisch
 * https://imagej.net/Multiview-Reconstruction
 * https://github.com/PreibischLab/multiview-reconstruction
 */
public class BoundingBox implements RealInterval, Comparable< BoundingBox >
{
	protected double[] min, max;
	protected String title;

	public BoundingBox( final String title, final double[] min, final double[] max )
	{
		this.title = title;
		this.min = min;
		this.max = max;
	}

	public BoundingBox(final double[] min, final double[] max )
	{
		this.min = min;
		this.max = max;
		this.title = "DefaultBoundingBox";
	}

	public void setTitle( final String title ) { this.title = title; }
	public String getTitle() { return title; }

	public double[] getMin() { return min; }
	public double[] getMax() { return max; }

//	/**
//	 * @param downsampling - how much downsampling (TODO: remove)
//	 * @return - the final dimensions including downsampling of this bounding box (to instantiate an img)
//	 */
//	public long[] getDimensions( final int downsampling )
//	{
//		final long[] dim = new long[ this.numDimensions() ];
//		this.dimensions( dim );
//
//		for ( int d = 0; d < this.numDimensions(); ++d )
//			dim[ d ] /= downsampling;
//
//		return dim;
//	}

	@Override
	public double realMin( final int d ) { return min[ d ]; }

	@Override
	public void realMin( final double[] min )
	{
		for ( int d = 0; d < min.length; ++d )
			min[ d ] = this.min[ d ];
	}

	@Override
	public void realMin( final RealPositionable min ) { min.setPosition( this.min ); }

	@Override
	public double realMax( final int d ) { return this.max[ d ]; }

	@Override
	public void realMax( final double[] max )
	{
		for ( int d = 0; d < max.length; ++d )
			max[ d ] = this.max[ d ];
	}

	@Override
	public void realMax( final RealPositionable max ) { max.setPosition( this.max ); }

	@Override
	public int numDimensions() { return min.length; }

	@Override
	public int compareTo( final BoundingBox o ) { return o.getTitle().compareTo( this.getTitle() ); }

	@Override
	public String toString()
	{
		return "Bounding Box '" + getTitle() + "' " + Util.printCoordinates( min ) + " >>> " + Util.printCoordinates( max );
	}

}
