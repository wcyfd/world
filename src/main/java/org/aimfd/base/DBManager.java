package org.aimfd.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 数据库管理器
 * 
 * @author AIM
 *
 */
public class DBManager {

	/**
	 * 根据指定dao方法进行查询
	 * 
	 * @param tableClazz
	 *            表结构
	 * @param entity
	 *            需要注入的实体
	 * @param methodName
	 *            dao的方法名
	 * @param parameterTypes
	 *            方法的参数类型
	 * @param objects
	 *            参数的值
	 * @return
	 */
	public static <ENTITY, TABLE extends AbstractTable, DAO extends BaseDao<TABLE, ? extends Serializable>> TABLE loadTable(Class<TABLE> tableClazz, ENTITY entity, String methodName,
			Class<?>[] parameterTypes, Object[] objects) {
		BaseDao<TABLE, ? extends Serializable> dao = findDao(tableClazz);

		try {
			// 从数据库拿出来
			Method method = dao.getClass().getMethod(methodName, parameterTypes);
			@SuppressWarnings("unchecked")
			TABLE table = (TABLE) method.invoke(dao, objects);

			// 导入到对应的实体中
			DBUtils.loadData(table, entity);
			return table;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 根据指定dao方法进行查询
	 * 
	 * @param tableClazz
	 *            表结构
	 * @param entity
	 *            需要注入的实体
	 * @param methodName
	 *            dao的方法名
	 * @param parameterTypes
	 *            方法的参数类型
	 * @param objects
	 *            参数的值
	 * @param dbLoader
	 *            自定义的加载器
	 * @return
	 */
	public static <ENTITY, TABLE extends AbstractTable, DAO extends BaseDao<TABLE, ? extends Serializable>> TABLE loadTable(Class<TABLE> tableClazz, ENTITY entity, String methodName,
			Class<?>[] parameterTypes, Object[] objects, DBDataLoader<TABLE, ENTITY> dbLoader) {
		BaseDao<TABLE, ? extends Serializable> dao = findDao(tableClazz);

		try {
			// 从数据库拿出来
			Method method = dao.getClass().getMethod(methodName, parameterTypes);
			@SuppressWarnings("unchecked")
			TABLE table = (TABLE) method.invoke(dao, objects);

			// 使用自定义的加载器导入到对应的实体中
			dbLoader.load(table, entity);

			return table;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param tableClazz
	 * @param entities
	 * @param entityClass
	 * @param methodName
	 * @param parameterTypes
	 * @param objects
	 * @return
	 */
	public static <ENTITY, TABLE extends AbstractTable, DAO extends BaseDao<TABLE, ? extends Serializable>> List<TABLE> loadTables(Class<TABLE> tableClazz, List<ENTITY> entities,
			Class<ENTITY> entityClass, String methodName, Class<?>[] parameterTypes, Object[] objects) {
		BaseDao<TABLE, ? extends Serializable> dao = findDao(tableClazz);

		try {
			// 从数据库拿出来
			Method method = dao.getClass().getMethod(methodName, parameterTypes);
			@SuppressWarnings("unchecked")
			List<TABLE> tables = (List<TABLE>) method.invoke(dao, objects);

			// 导入到对应的实体中
			DBUtils.loadDataList(tables, entities, entityClass);
			return tables;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 获取DAO
	 * 
	 * @param tableClazz
	 * @return
	 */
	private static <TABLE extends AbstractTable> BaseDao<TABLE, ? extends Serializable> findDao(Class<TABLE> tableClazz) {
		Table tableAnnotation = tableClazz.getAnnotation(Table.class);
		@SuppressWarnings("unchecked")
		Class<? extends BaseDao<TABLE, ? extends Serializable>> daoClass = (Class<? extends BaseDao<TABLE, ? extends Serializable>>) tableAnnotation.value();
		BaseDao<TABLE, ? extends Serializable> dao = SpringContext.getContext().getBean(daoClass);
		return dao;
	}

	/**
	 * 使用数据表中默认的主键从数据库中找到数据
	 * 
	 * @param tableClazz
	 *            表结构
	 * @param entity
	 *            需要注入的实体
	 * @param id
	 *            默认主键的值
	 * @return
	 */
	public static <ENTITY, TABLE extends AbstractTable, ID extends Serializable> TABLE loadTable(Class<TABLE> tableClazz, ENTITY entity, ID id) {
		BaseDao<TABLE, ID> dao = findDaoByDefaultID(tableClazz);

		try {
			// 从数据库拿出来
			TABLE table = dao.find(id);

			// 导入到对应的实体中
			DBUtils.loadData(table, entity);

			return table;
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static <TABLE extends AbstractTable, ID extends Serializable> BaseDao<TABLE, ID> findDaoByDefaultID(Class<TABLE> tableClazz) {
		Table tableAnnotation = tableClazz.getAnnotation(Table.class);
		@SuppressWarnings("unchecked")
		Class<? extends BaseDao<TABLE, ID>> daoClass = (Class<? extends BaseDao<TABLE, ID>>) tableAnnotation.value();
		BaseDao<TABLE, ID> dao = SpringContext.getContext().getBean(daoClass);
		return dao;
	}

	/**
	 * 保存表数据
	 * 
	 * @param sourceTable
	 * @param targetTable
	 */
	public static <TABLE extends AbstractTable> void saveTable(TABLE sourceTable, TABLE targetTable) {
		if (DBUtils.needSaveData(sourceTable, targetTable)) {
			
		}
	}

}
