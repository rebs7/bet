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

public class ZeroZeroNextGames implements Runnable {

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect("http://www.zerozero.pt/proximos_jogos.php").get();
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
				if (!element.getElementsByTag("td").eq(7).text().isEmpty()) {
					jogo.setCompeticao(element.getElementsByTag("td").eq(7).text().replace("2016/2017","").replace("2016/17", "").replace("16/17", "").replace("2016", "").replace("16", ""));
				}
				if (!element.getElementsByTag("td").eq(8).text().isEmpty()) {
					jogo.setOdds1(Double.parseDouble(element.getElementsByTag("td").eq(8).text()));
					jogo.setOddsx(Double.parseDouble(element.getElementsByTag("td").eq(9).text()));
					jogo.setOdds2(Double.parseDouble(element.getElementsByTag("td").eq(10).text()));

				}
				if (!equipaA.exists()) {
					equipaA.add();
					
				}
				if (!equipaH.exists()) {
					equipaH.add();
				}
				if (!jogo.exists()) {
					jogo.add();
					jogo.updateState();
				}else{
					jogo.updateState();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		(new Thread(new ZeroZeroNextGames())).start();


	
	}
}
