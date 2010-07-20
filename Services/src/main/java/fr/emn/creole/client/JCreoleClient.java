package fr.emn.creole.client;

/**
 * Created by IntelliJ IDEA.
 * User: mayleen
 * Date: Jul 19, 2010
 * Time: 4:52:44 PM
 * To change this template use File | Settings | File Templates.
 */

import fr.emn.creole.core.Atom;
import fr.emn.creole.model.*;
import fr.emn.creole.util.JSONUtil;

import java.net.URI;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.config.*;
import sjson.json.*;

public class JCreoleClient {

    public void exportAtom(Atom atom, URI url){
//        Client klient = Client.create(new DefaultClientConfig());
//        WebResource vmService = klient.resource(url).path(atom.relName);
//
//        String content = JSONUtil.serialize(new VarList(atom));
//        String resp = "";
//
//        try{
//            vmService.entity(content.getBytes(), MediaType.APPLICATION_JSON_TYPE).
//            put(String.class);
//        }catch(Exception e){
//          System.out.println("Error: " + e);
//        }
    }
}
