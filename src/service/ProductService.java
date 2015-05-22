/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import java.util.Properties;


public interface ProductService {
    public List<String> getAllIdsBySearch(String search);
    public Properties getProductSmallInfo(String id, String language);
    public Properties getProductBigInfo(String id, String language);
}
