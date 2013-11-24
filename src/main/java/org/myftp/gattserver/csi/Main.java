package org.myftp.gattserver.csi;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.myftp.gattserver.csi.director.WorldGenerator;
import org.myftp.gattserver.csi.world.Knowledge;
import org.myftp.gattserver.csi.world.World;
import org.myftp.gattserver.csi.world.relations.IRelationType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	private static ApplicationContext context;

	public static void main(String[] args) {

		context = new ClassPathXmlApplicationContext("spring/app-context.xml");

		WorldGenerator worldGenerator = context.getBean(WorldGenerator.class);
		World world = context.getBean(World.class);

		Knowledge knowledge = worldGenerator.generateWorldKnowledge();

		NumberFormat numberFormat = new DecimalFormat("###.##");
		logger.info("World population: " + world.getPopulation());
		logger.info("World AVG age: "
				+ numberFormat.format(world.getAverageAge()));
		logger.info("World MAX age: "
				+ numberFormat.format(world.getMaximumAge()));
		logger.info("World MIN age: "
				+ numberFormat.format(world.getMinimumAge()));
		logger.info("World males: " + world.getNumberOfMales());
		logger.info("World females: " + world.getNumberOfFemales());
		logger.info("World M/F ratio: "
				+ numberFormat.format(world.getNumberOfMales() * 1.0
						/ world.getNumberOfFemales()));

		for (IRelationType type : world.getRelationTypes()) {
			logger.info("Number of relations of type '" + type.getName()
					+ "': " + world.getPersonsByRelations(type).size());

		}

	}
}
