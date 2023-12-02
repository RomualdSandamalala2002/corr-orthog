package mg.sanda.corrorthog.utils;

/**
 * Requête du « body » http sous méthode POST
 */
public class PayloadRequestBody {
    private String sentence;
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public PayloadRequestBody(){}

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
