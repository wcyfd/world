package org.aimfd.base;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class DBUtils {
	/**
	 * 载入数据
	 * 
	 * @param table
	 * @param entity
	 */
	public static <TABLE, ENTITY> void loadData(TABLE table, ENTITY entity) {
		Field[] fields = table.getClass().getFields();
		for (Field field : fields) {

			Object tableFieldValue = getFieldValue(table, field);
			Column column = field.getAnnotation(Column.class);

			injectValue(tableFieldValue, entity, column.value(), column.paramType());
		}
	}

	public static <TABLE, ENTITY> void loadDataList(List<TABLE> tables, List<ENTITY> entities, Class<ENTITY> entityClass) {
		for (TABLE table : tables) {
			try {
				ENTITY entity = entityClass.newInstance();
				DBUtils.loadData(table, entity);
				entities.add(entity);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private static void injectValue(Object tableFieldValue, Object entity, String methodName, Class<?> paramType) {
		try {
			Method method = entity.getClass().getMethod(methodName, paramType);
			method.invoke(entity, tableFieldValue);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private static Object getFieldValue(Object obj, Field field) {
		try {
			return field.get(obj);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <TABLE> boolean needSaveData(TABLE source, TABLE target) {
		Class<?> clazz = source.getClass();

		Field[] fields = clazz.getFields();
		boolean save = false;
		for (Field field : fields) {
			// 获取原数据的值
			Object sourceValue = getFieldValue(source, field);
			// 获取现有数据的值
			Object targetValue = getFieldValue(target, field);

			// 有值从空值变成非空值
			if ((sourceValue == null && targetValue != null) || (sourceValue != null && targetValue == null)) {
				save = true;
				break;
			}
			// 如果两个值不相等，则进行保存
			if (!sourceValue.equals(targetValue)) {
				save = true;
				break;
			}
		}
		return save;

	}

}
