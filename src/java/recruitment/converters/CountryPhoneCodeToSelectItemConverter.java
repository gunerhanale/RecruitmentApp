/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.converters;

import javax.faces.model.SelectItem;
import common.util.Converter;
import recruitment.entitites.PhoneCountryCode;


/**
 *
 * @author ebubekir.gunerhanal
 */
public class CountryPhoneCodeToSelectItemConverter implements Converter<PhoneCountryCode, SelectItem>{

    @Override
    public SelectItem convert(PhoneCountryCode obj) {
        return new SelectItem(obj.getCountryId(), obj.getCountry() + obj.getDialing());
    }
    
}
