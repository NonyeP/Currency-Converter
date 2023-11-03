package converter;

/* This class gets live currency readings from the exchange-rate api

 */

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;

import org.json.JSONObject;



public class CurrencyConverter {

    //hide api key and url in environment setting
    private static final String API_KEY = System.getenv("CURRENCY_CONVERTER_API_KEY");
    private static final String API_URL = System.getenv("CURRENCY_CONVERTER_API_URL");

    public static void main(String[] args) {
        try {
            double amount = 100.0; // Replace with your desired amount
            String sourceCurrency = "GBP"; // Replace with your source currency code
            String destinationCurrency = "NGN"; // Replace with your target currency code

            double convertedAmount = convertCurrency(sourceCurrency, destinationCurrency, amount);
            System.out.println(amount + " " + sourceCurrency + " = " + convertedAmount + " " + destinationCurrency);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static double convertCurrency(String sourceCurrency, String destinationCurrency, double amount) throws Exception {

        String urlStr = API_URL + "?apiKey=" + API_KEY;
        URL url = new URL(urlStr);
        double convertedAmount = 0;
        double rate = 0;
        Currency destinationCurrencySymbol = Currency.getInstance(destinationCurrency);
        Currency sourceCurrencySymbol = Currency.getInstance(sourceCurrency);

        if (API_URL == null || API_KEY == null) {
            throw new RuntimeException("CURRENCY_CONVERTER_API_KEY or CURRENCY_CONVERTER_API_URL environment variable is not set.");
        }


        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            //API response
            JSONObject obj = new JSONObject(response.toString());
            double exchangeRateSource = obj.getJSONObject("conversion_rates").getDouble(sourceCurrency);
            System.out.println("1 unit of " + sourceCurrency + " = " + exchangeRateSource);

            double exchangeRateDestination = obj.getJSONObject("conversion_rates").getDouble(destinationCurrency);
            System.out.println("1 unit of " + destinationCurrency + " = " + exchangeRateDestination );
            rate = exchangeRateDestination/exchangeRateSource;

            System.out.println("The exchange rate of 1 " + sourceCurrency + " to " + destinationCurrency + " is " + rate);//keep for debugging
            convertedAmount = amount*rate;
            System.out.println();

            //System.out.println("API Response: " + response.toString());
            System.out.println("Rate = " + rate);
            System.out.println("(" + sourceCurrency + ") "  + sourceCurrencySymbol.getSymbol()
                    + amount + " = (" + destinationCurrency + ") " + destinationCurrencySymbol.getSymbol() + convertedAmount);
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertedAmount;
    }

    public String[] getCurrencyCodes() {
        return new String[] {"JPY","EUR","GBP","USD","CAD","INR","NGN","CNY"};// user can add more currency codes here
    }

}



    /* Currencies you can add to the string array from exchange-rate api
                AED	UAE Dirham	United Arab Emirates
                AFN	Afghan Afghani	Afghanistan
                ALL	Albanian Lek	Albania
                AMD	Armenian Dram	Armenia
                ANG	Netherlands Antillian Guilder	Netherlands Antilles
                AOA	Angolan Kwanza	Angola
                ARS	Argentine Peso	Argentina
                AUD	Australian Dollar	Australia
                AWG	Aruban Florin	Aruba
                AZN	Azerbaijani Manat	Azerbaijan
                BAM	Bosnia and Herzegovina Mark	Bosnia and Herzegovina
                BBD	Barbados Dollar	Barbados
                BDT	Bangladeshi Taka	Bangladesh
                BGN	Bulgarian Lev	Bulgaria
                BHD	Bahraini Dinar	Bahrain
                BIF	Burundian Franc	Burundi
                BMD	Bermudian Dollar	Bermuda
                BND	Brunei Dollar	Brunei
                BOB	Bolivian Boliviano	Bolivia
                BRL	Brazilian Real	Brazil
                BSD	Bahamian Dollar	Bahamas
                BTN	Bhutanese Ngultrum	Bhutan
                BWP	Botswana Pula	Botswana
                BYN	Belarusian Ruble	Belarus
                BZD	Belize Dollar	Belize
                CAD	Canadian Dollar	Canada
                CDF	Congolese Franc	Democratic Republic of the Congo
                CHF	Swiss Franc	Switzerland
                CLP	Chilean Peso	Chile
                CNY	Chinese Renminbi	China
                COP	Colombian Peso	Colombia
                CRC	Costa Rican Colon	Costa Rica
                CUP	Cuban Peso	Cuba
                CVE	Cape Verdean Escudo	Cape Verde
                CZK	Czech Koruna	Czech Republic
                DJF	Djiboutian Franc	Djibouti
                DKK	Danish Krone	Denmark
                DOP	Dominican Peso	Dominican Republic
                DZD	Algerian Dinar	Algeria
                EGP	Egyptian Pound	Egypt
                ERN	Eritrean Nakfa	Eritrea
                ETB	Ethiopian Birr	Ethiopia
                EUR	Euro	European Union
                FJD	Fiji Dollar	Fiji
                FKP	Falkland Islands Pound	Falkland Islands
                FOK	Faroese Króna	Faroe Islands
                GBP	Pound Sterling	United Kingdom
                GEL	Georgian Lari	Georgia
                GGP	Guernsey Pound	Guernsey
                GHS	Ghanaian Cedi	Ghana
                GIP	Gibraltar Pound	Gibraltar
                GMD	Gambian Dalasi	The Gambia
                GNF	Guinean Franc	Guinea
                GTQ	Guatemalan Quetzal	Guatemala
                GYD	Guyanese Dollar	Guyana
                HKD	Hong Kong Dollar	Hong Kong
                HNL	Honduran Lempira	Honduras
                HRK	Croatian Kuna	Croatia
                HTG	Haitian Gourde	Haiti
                HUF	Hungarian Forint	Hungary
                IDR	Indonesian Rupiah	Indonesia
                ILS	Israeli New Shekel	Israel
                IMP	Manx Pound	Isle of Man
                INR	Indian Rupee	India
                IQD	Iraqi Dinar	Iraq
                IRR	Iranian Rial	Iran
                ISK	Icelandic Króna	Iceland
                JEP	Jersey Pound	Jersey
                JMD	Jamaican Dollar	Jamaica
                JOD	Jordanian Dinar	Jordan
                JPY	Japanese Yen	Japan
                KES	Kenyan Shilling	Kenya
                KGS	Kyrgyzstani Som	Kyrgyzstan
                KHR	Cambodian Riel	Cambodia
                KID	Kiribati Dollar	Kiribati
                KMF	Comorian Franc	Comoros
                KRW	South Korean Won	South Korea
                KWD	Kuwaiti Dinar	Kuwait
                KYD	Cayman Islands Dollar	Cayman Islands
                KZT	Kazakhstani Tenge	Kazakhstan
                LAK	Lao Kip	Laos
                LBP	Lebanese Pound	Lebanon
                LKR	Sri Lanka Rupee	Sri Lanka
                LRD	Liberian Dollar	Liberia
                LSL	Lesotho Loti	Lesotho
                LYD	Libyan Dinar	Libya
                MAD	Moroccan Dirham	Morocco
                MDL	Moldovan Leu	Moldova
                MGA	Malagasy Ariary	Madagascar
                MKD	Macedonian Denar	North Macedonia
                MMK	Burmese Kyat	Myanmar
                MNT	Mongolian Tögrög	Mongolia
                MOP	Macanese Pataca	Macau
                MRU	Mauritanian Ouguiya	Mauritania
                MUR	Mauritian Rupee	Mauritius
                MVR	Maldivian Rufiyaa	Maldives
                MWK	Malawian Kwacha	Malawi
                MXN	Mexican Peso	Mexico
                MYR	Malaysian Ringgit	Malaysia
                MZN	Mozambican Metical	Mozambique
                NAD	Namibian Dollar	Namibia
                NGN	Nigerian Naira	Nigeria
                NIO	Nicaraguan Córdoba	Nicaragua
                NOK	Norwegian Krone	Norway
                NPR	Nepalese Rupee	Nepal
                NZD	New Zealand Dollar	New Zealand
                OMR	Omani Rial	Oman
                PAB	Panamanian Balboa	Panama
                PEN	Peruvian Sol	Peru
                PGK	Papua New Guinean Kina	Papua New Guinea
                PHP	Philippine Peso	Philippines
                PKR	Pakistani Rupee	Pakistan
                PLN	Polish Złoty	Poland
                PYG	Paraguayan Guaraní	Paraguay
                QAR	Qatari Riyal	Qatar
                RON	Romanian Leu	Romania
                RSD	Serbian Dinar	Serbia
                RUB	Russian Ruble	Russia
                RWF	Rwandan Franc	Rwanda
                SAR	Saudi Riyal	Saudi Arabia
                SBD	Solomon Islands Dollar	Solomon Islands
                SCR	Seychellois Rupee	Seychelles
                SDG	Sudanese Pound	Sudan
                SEK	Swedish Krona	Sweden
                SGD	Singapore Dollar	Singapore
                SHP	Saint Helena Pound	Saint Helena
                SLE	Sierra Leonean Leone	Sierra Leone
                SOS	Somali Shilling	Somalia
                SRD	Surinamese Dollar	Suriname
                SSP	South Sudanese Pound	South Sudan
                STN	São Tomé and Príncipe Dobra	São Tomé and Príncipe
                SYP	Syrian Pound	Syria
                SZL	Eswatini Lilangeni	Eswatini
                THB	Thai Baht	Thailand
                TJS	Tajikistani Somoni	Tajikistan
                TMT	Turkmenistan Manat	Turkmenistan
                TND	Tunisian Dinar	Tunisia
                TOP	Tongan Paʻanga	Tonga
                TRY	Turkish Lira	Turkey
                TTD	Trinidad and Tobago Dollar	Trinidad and Tobago
                TVD	Tuvaluan Dollar	Tuvalu
                TWD	New Taiwan Dollar	Taiwan
                TZS	Tanzanian Shilling	Tanzania
                UAH	Ukrainian Hryvnia	Ukraine
                UGX	Ugandan Shilling	Uganda
                USD	United States Dollar	United States
                UYU	Uruguayan Peso	Uruguay
                UZS	Uzbekistani So'm	Uzbekistan
                VES	Venezuelan Bolívar Soberano	Venezuela
                VND	Vietnamese Đồng	Vietnam
                VUV	Vanuatu Vatu	Vanuatu
                WST	Samoan Tālā	Samoa
                XAF	Central African CFA Franc	CEMAC
                XCD	East Caribbean Dollar	Organisation of Eastern Caribbean States
                XDR	Special Drawing Rights	International Monetary Fund
                XOF	West African CFA franc	CFA
                XPF	CFP Franc	Collectivités d'Outre-Mer
                YER	Yemeni Rial	Yemen
                ZAR	South African Rand	South Africa
                ZMW	Zambian Kwacha	Zambia
                ZWL	Zimbabwean Dollar


                                    */

