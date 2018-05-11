package org.aimfd.world.planet.terrain.manager.module;

import org.aimfd.world.planet.Planet;
import org.aimfd.world.planet.terrain.data.ITerrainData;
import org.aimfd.world.planet.terrain.data.unit.ITerrainUnitData;

/**
 * 地图初始化模块
 * 
 * @author AIM
 *
 */
public class TerrainInitModule {

	private ITerrainData terrainData;

	public TerrainInitModule(Planet planet) {
		this.terrainData = planet.getPlanetAllData().getPlanetData().getTerrainData();
	}

	public void terrainInit(int width, int height) {
		terrainData.setTerrainWidth(width);
		terrainData.setTerrainHeight(height);
		terrainData.setTerrainUnitCount(0);

		int LEFT_TOP = 0;
		int RIGHT_TOP = 1;
		int RIGHT = 2;
		int RIGHT_BUTTOM = 3;
		int LEFT_BUTTOM = 4;
		int LEFT = 5;

		// 根据地图长宽进行瓦片绑定
		boolean isNewLineFirst = true;
		boolean isOdd = true;
		int tempFirstId = 0;
		int lastId = -1;
		for (int id = 0, j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
				ITerrainUnitData unitData = terrainData.getTerrainUnitData(id);

				/****** 绑定左边 *****/
				// 新的一行
				if (isNewLineFirst) {
					tempFirstId = id;// 记录上一行的第一个板块id
				} else {
					// 绑定左边,双向绑定
					ITerrainUnitData leftUnitData = terrainData.getTerrainUnitData(id - 1);
					unitData.bindTerrainUnitData(LEFT, leftUnitData);
					leftUnitData.bindTerrainUnitData(RIGHT, unitData);
				}

				boolean useLastId = false;
				/******** 绑定左上 ********/
				// 绑定左上,双向绑定
				if (j != 0) {// 第一行就跳过
					if (isOdd) {// 奇数行
						if (!isNewLineFirst) {// 奇数行并且是第一个没有左上
							ITerrainUnitData leftTopUnitData = terrainData.getTerrainUnitData(lastId);
							unitData.bindTerrainUnitData(LEFT_TOP, leftTopUnitData);
							leftTopUnitData.bindTerrainUnitData(RIGHT_BUTTOM, unitData);
							useLastId = true;
						}
					} else {// 偶数行直接开始绑定
						ITerrainUnitData leftTopUnitData = terrainData.getTerrainUnitData(lastId);
						unitData.bindTerrainUnitData(LEFT_TOP, leftTopUnitData);
						leftTopUnitData.bindTerrainUnitData(RIGHT_BUTTOM, unitData);
						useLastId = true;
					}
				}

				/********* 绑定右上 *********/
				// 绑定右上,双向绑定
				if (j != 0) {// 第一行就跳过
					if (isOdd) {// 奇数行
						// 奇数行最后一个没有右上
						if (i + 1 != width) {
							ITerrainUnitData rightTopUnitData = terrainData.getTerrainUnitData(lastId + 1);
							unitData.bindTerrainUnitData(RIGHT_TOP, rightTopUnitData);
							rightTopUnitData.bindTerrainUnitData(LEFT_BUTTOM, unitData);
						}
					} else {// 偶数
						ITerrainUnitData rightTopUnitData = terrainData.getTerrainUnitData(lastId + 1);
						unitData.bindTerrainUnitData(RIGHT_TOP, rightTopUnitData);
						rightTopUnitData.bindTerrainUnitData(LEFT_BUTTOM, unitData);
					}
				}

				isNewLineFirst = false;
				if (useLastId)
					lastId++;
				id++;
				terrainData.setTerrainUnitCount(terrainData.getTerrainUnitCount() + 1);
			}

			lastId = tempFirstId;
			isOdd = !isOdd;
			width += isOdd ? 1 : -1;
			isNewLineFirst = true;
		}

	}

}
