package org.myftp.gattserver.csi;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.myftp.gattserver.csi.director.WorldGenerator;
import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {

		WorldGenerator worldGenerator = new WorldGenerator();
		Knowledge knowledge = worldGenerator.generateWorldKnowledge();

		NumberFormat numberFormat = new DecimalFormat("###.##");
		logger.info("World population: " + Statistics.INSTANCE.getPopulation());
		logger.info("World AVG age: "
				+ numberFormat.format(Statistics.INSTANCE.getAverageAge()));
		logger.info("World MAX age: "
				+ numberFormat.format(Statistics.INSTANCE.getMaximumAge()));
		logger.info("World MIN age: "
				+ numberFormat.format(Statistics.INSTANCE.getMinimumAge()));
		logger.info("World males: " + Statistics.INSTANCE.getNumberOfMales());
		logger.info("World females: "
				+ Statistics.INSTANCE.getNumberOfFemales());
		logger.info("World M/F ratio: "
				+ numberFormat.format(Statistics.INSTANCE.getNumberOfMales()
						* 1.0 / Statistics.INSTANCE.getNumberOfFemales()));

	}
}