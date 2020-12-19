/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recruitment.converters;

import javax.faces.model.SelectItem;
import common.util.Converter;
import recruitment.entitites.Candidates;


/**
 *
 * @author ebubekir.gunerhanal
 */
public class CandidateToSelectItemConverter implements Converter<Candidates, SelectItem>{

    @Override
    public SelectItem convert(Candidates obj) {
        return new SelectItem(obj.getId(), obj.getFullName());
    }
    
}
