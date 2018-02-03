package com.patrickgrimard;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static com.patrickgrimard.JsonTransformer.toJson;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import static spark.Spark.port;

/**
 * Created by Patrick on 2015-06-23.
 */
public class HelloWorld {

    private static final List<Pizza> pizzas = new ArrayList<Pizza>();

    public static void main(String[] args) {
    	// https://scontent-cdg2-1.xx.fbcdn.net/v/t1.0-9/23316638_10150891581049986_7143599378227437081_n.jpg?oh=0d0d7a7fb9c626d2b53bf443573f15b7&oe=5B22A999
    	pizzas.add(new Pizza("Carlo", "sauce tomate, mozzarella, jambon", 8.50));
    	pizzas.add(new Pizza("Regina", "sauce tomate, mozzarella, jambon, champignons frais", 10.0));
    	pizzas.add(new Pizza("Emile", "sauce tomate, mozzarella, jambon, champignons frais, gorgonzola, oeuf", 11.60));
    	pizzas.add(new Pizza("Forestière", "sauce tomate, emmental, reblochon, champignons frais", 10.50));
    	pizzas.add(new Pizza("4 saisons", "sauce tomate, mozzarella, jambon, champignons frais, poivrons, artichauds, oeuf", 11.80));
    	pizzas.add(new Pizza("Végétarienne", "Sauce tomate, champignons, poivrons, aubergines, oignons, artichauds, courgettes ", 10.80));
    	pizzas.add(new Pizza("Méridionale", "sauce tomate, mozzarella, aubergines, poivrons, courgettes, oignons, chêvre", 11.0));
    	pizzas.add(new Pizza("Chevrette", "Sauce tomate, emmental, feuille d'épinards, chèvre, crème", 10.90));
    	pizzas.add(new Pizza("Vesuvio", "Sauce tomate, mozzarella, chorizo, poivrons", 11.80));
    	pizzas.add(new Pizza("Moussaka", "Sauce tomate, haché de boeuf, mozzarella, aubergines, oignons, crême", 11.90));
    	pizzas.add(new Pizza("Barbecue", "Sauce tomate, haché de boeuf, merguez, poivrons, mozzarella", 11.90));
    	pizzas.add(new Pizza("Poulette", "Sauce tomate, blanc de poulet, poivrons, champignons frais, mozzarella", 12.20));
    	pizzas.add(new Pizza("Leonardo", "Sauce tomate, poulet, chorizo, haché de boeuf, poivrons, mozzarella", 13.80));
    	pizzas.add(new Pizza("Bouchère", "Sauce tomate, jambon, chorizo, haché de boeuf, mozzarella", 12.00));
    	pizzas.add(new Pizza("Bolognaise", "Sauce tomate, orecchiettes, haché de boeuf, mozzarella, parmesan", 10.80));
    	pizzas.add(new Pizza("Michelangelo", "Sauce tomate, mozzarella, roquette, jambon cru, parmesan", 13.80));
    	pizzas.add(new Pizza("Orientale", "Sauce tomate, merguez, oeuf, emmental", 10.80));
    	pizzas.add(new Pizza("Méditerranéenne", "Sauce tomate, viande de kebab, merguez, mozzarella", 12.80));
    	pizzas.add(new Pizza("Pouguez", "Sauce tomate, blanc de poulet, merguez, mozzarella", 12.50));
    	pizzas.add(new Pizza("Kebab", "Sauce tomate, viande de kebab, oignons, mozzarrella, roquette", 12.80));
    	pizzas.add(new Pizza("Seguin", "Sauce tomate, mozzarella, lardons, chèvre", 10.50));
    	pizzas.add(new Pizza("Raphael", "Sauce tomate, poulet mariné au curry, ananas, mozzarella, oignons", 12.50));
    	pizzas.add(new Pizza("Hawaïie", "Sauce tomate, ananas, queues de crevette, oignons, crème, mozzarella", 12.80));
    	pizzas.add(new Pizza("Napolitana", "Sauce tomate, mozzarella, anchois, câpres", 10.00));
    	pizzas.add(new Pizza("Albacore", "Sauce tomate, mozzarella, thon, crême, citron", 10.00));
    	pizzas.add(new Pizza("Maya", "sauce tomate, chèvre, emmental, miel, noix, roquette", 11.90));
    	pizzas.add(new Pizza("4 fromages", "Sauce tomate, mozzarella, emmental, gorgonzola, chèvre", 10.50));
    	pizzas.add(new Pizza("BIS", "Sauce tomate, emmental, mozzarella, reblochon, saint-marcellin", 10.80));
    	pizzas.add(new Pizza("Calzone", "Sauce tomate, jambon, emmental, mozzarella, oeuf", 9.20));
    	pizzas.add(new Pizza("Calzone gorgon", "sauce tomate, emmental, mozzarella, gorgonzola, oeuf", 9.50));
    	pizzas.add(new Pizza("Calzone chèvre", "Sauce tomate, jambon, emmental, mozzarella, chèvre, oeuf", 9.80));
    	pizzas.add(new Pizza("Reine blanche", "Crème, mozzarella, jambon, champignons frais", 10.30));
    	pizzas.add(new Pizza("Saint Jean", "crème, emmental, ravioles, Saint-Marcellin, noix, ciboullette", 11.80));
    	pizzas.add(new Pizza("Savoyarde", "crème, emmental, oignons, reblochon, jambon cru", 11.00));
    	pizzas.add(new Pizza("Tartifle", "crème, pomme de terre, oignons, lardons, reblochon, emmental", 11.80));
    	pizzas.add(new Pizza("Alpine", "crème, oignons, pommes de terre, diots, reblochon, emmental", 12.50));
    	pizzas.add(new Pizza("Cour'che", "crème, courgettes, chèvre, oignons, emmental", 12.00));
    	pizzas.add(new Pizza("Donatello", "crème, mozzarella, roquette, saumon fumé", 13.80));

        staticFileLocation("/public");
        port(8080);
        before((req, res) -> res.type("application/json"));
        Map<String, List> pizzaJsonObj = new HashMap<String, List>();
        pizzaJsonObj.put("pizzas", pizzas);
        get("/pizzas", (req, res) -> toJson(pizzaJsonObj));
    }
  public static class Pizza {
	  
	  public String Name;
	  public String Desc;
	  public double price;
	  
	  public Pizza(String N, String D, double e) {
		  this.Name  = N;
		  this.Desc  = D;
		  this.price = e;
	  }
  }
}
