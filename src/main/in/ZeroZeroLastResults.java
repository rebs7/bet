package main.in;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.entities.Equipas;
import main.entities.Jogos;
import main.entities.JogosId;
import main.utils.Utils;

public class ZeroZeroLastResults implements Runnable {

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect("http://www.zerozero.pt/ultimos_resultados.php").get();
			Elements parent = doc.getElementsByClass("parent");
			SimpleDateFormat formatter = null;
			Date date = null;
			for (Element element : parent) {
				Equipas equipaH = new Equipas(element.getElementsByTag("td").eq(3).text());
				Equipas equipaA = new Equipas(element.getElementsByTag("td").eq(5).text());
				if (element.getElementsByTag("td").eq(2).text().isEmpty()) {
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					date = formatter.parse(element.getElementsByTag("td").eq(1).text());
				} else {
					formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					date = formatter.parse(element.getElementsByTag("td").eq(1).text() + " "+ element.getElementsByTag("td").eq(2).text());
				}
				
				Jogos jogo = new Jogos(	new JogosId(equipaH.getNome(), equipaA.getNome(), element.getElementsByTag("td").eq(1).text()));
				jogo.setDataReal(date);
				String [] resultado= element.getElementsByTag("td").eq(4).text().split("-");
				
				jogo.setGolos1(Integer.parseInt(resultado[0]));
				jogo.setGolos2(Integer.parseInt(resultado[0]));
				
				if (!element.getElementsByTag("td").eq(7).text().isEmpty()) {					jogo.setCompeticao(Utils.competitionsFilter(element.getElementsByTag("td").eq(7).text()));
				}
				if(jogo.getCompeticao()!=null){
				if (!equipaA.exists()) {
					equipaA.add();
					
				}
				if (!equipaH.exists()) {
					equipaH.add();
				}
				if (!jogo.exists()) {
					jogo.setEstado();
					jogo.add();
				
				}else{
					jogo.update();
					
				}}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		(new Thread(new ZeroZeroLastResults())).start();


	
	}
}
