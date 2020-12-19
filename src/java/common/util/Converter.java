package common.util;

public interface Converter <T,R>
{
	/**
	 * Converts one object (leaving it unchanged) to another
	 * @param toBeConverted object to be converted
	 * @return convertedObject
	 */
	public R convert(T toBeConverted) ;


}