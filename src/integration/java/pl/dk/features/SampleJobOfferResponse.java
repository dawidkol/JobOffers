package pl.dk.features;

interface SampleJobOfferResponse {

    default String offerNotFoundJson(String id) {
        return String.format("""
                {
                "status": "NOT_FOUND",
                "message": "offer with id = %s not found"
                }
                """, id);
    }

    default String bodyWithZeroJobOfferJson() {
        return "[]".trim();
    }

    default String bodyWithOneJobOfferJson() {
        return """
                [
                {
                        "title": "Junior Java Developer",
                        "company": "BlueSoft Sp. z o.o.",
                        "salary": "7 000 - 9 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre"
                }
                ]
                """.trim();
    }

    default String bodyWithTwoJobOffersJson() {
        return """
                [
                {
                        "title": "Junior Java Developer",
                        "company": "BlueSoft Sp. z o.o.",
                        "salary": "7 000 - 9 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-bluesoft-remote-hfuanrre"
                },
                {
                        "title": "Java (CMS) Developer",
                        "company": "Efigence SA",
                        "salary": "16 000 - 18 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/java-cms-developer-efigence-warszawa-b4qs8loh"
                }
                ]
                """.trim();
    }

    default String bodyWithTwoNewOffers() {
        return """
                [
                {
                        "title": "Junior Java Backend Developer",
                        "company": "Enigma SOI",
                        "salary": "6 300 - 12 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-backend-developer-enigma-soi-warszawa-ziaekkrf"
                    },
                    {
                        "title": "Junior Java Developer",
                        "company": "Fair Place Finance S.A.",
                        "salary": "6 000 - 9 000 PLN",
                        "offerUrl": "https://nofluffjobs.com/pl/job/junior-java-developer-fair-place-finance-remote-kxvnnhb1"
                    }
                    ]
                """.trim();
    }

    default String bodyWithOfferToSave() {
        return """ 
                {
                "title": "Junior Java Developer",
                "company": "Fair Place Finance S.A.",
                "salary": "6 000 - 9 000 PLN",
                "offerUrl": "https://www.ofertajunior.pl"
                }
                """.trim();
    }
}









