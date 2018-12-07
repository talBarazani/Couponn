package com.tal.couponsdemo.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.stereotype.Component;

import com.tal.couponsdemo.entities.CouponType;

@Component
@Converter(autoApply = true)
public class CouponTypeConverter implements AttributeConverter<CouponType, String> {

	@Override
	public String convertToDatabaseColumn(CouponType method) {
		switch(method){
			case ELECTRICTY:
				return "ELE";
			case FOOD:
				return "FOO";
			case LEISURE:
				return "LEI";
			case VACATION:
				return "VAC";
			default:
				throw new IllegalArgumentException("Unknown method: " + method);
		}
	}

	@Override
	public CouponType convertToEntityAttribute(String dbData) {
		switch(dbData){
		case "ELE" :
			return CouponType.ELECTRICTY;
		case "FOO":
			return CouponType.FOOD;
		case "LEI":
			return CouponType.LEISURE;
		case "VAC":
			return CouponType.VACATION;
		default:
			throw new IllegalArgumentException("Unknown value: " + dbData);
	}
	}
}


