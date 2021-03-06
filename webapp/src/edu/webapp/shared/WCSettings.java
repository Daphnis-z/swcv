package edu.webapp.shared;

import edu.webapp.shared.registry.WCAspectRatioRegistry;
import edu.webapp.shared.registry.WCColorSchemeRegistry;
import edu.webapp.shared.registry.WCFontRegistry;
import edu.webapp.shared.registry.WCLayoutAlgoRegistry;
import edu.webapp.shared.registry.WCRankingAlgoRegistry;
import edu.webapp.shared.registry.WCSimilarityAlgoRegistry;

import java.io.Serializable;

/**
 * @author spupyrev
 *         Aug 17, 2013
 */
public class WCSettings implements Serializable
{
    private static final long serialVersionUID = 5465297978880066047L;

    public WCSettings()
    {
    }

    private int wordCount = 50;

    private WCColorScheme colorScheme = WCColorSchemeRegistry.getDefault();
    private WCLayoutAlgo layoutAlgorithm = WCLayoutAlgoRegistry.getDefault();
    private WCSimilarityAlgo similarityAlgorithm = WCSimilarityAlgoRegistry.getDefault();
    private WCRankingAlgo rankingAlgorithm = WCRankingAlgoRegistry.getDefault();
    private WCFont font = WCFontRegistry.getDefault();
    private WCAspectRatio aspectRatio = WCAspectRatioRegistry.getDefault();

    //parse options
    private boolean removeStopwords = true;
    private boolean stemWords = true;
    private boolean removeNumbers = true;
    private int minWordLength = 3;
    private String language = "en";

    public void setRandomSetting()
    {
        setColorScheme(WCColorSchemeRegistry.getRandom());

        setLayoutAlgorithm(WCLayoutAlgoRegistry.getRandom());

        //setSimilarityAlgorithm(WCSimilarityAlgoRegistry.getRandom());

        //setRankingAlgorithm(WCRankingAlgoRegistry.getRandom());

        setFont(WCFontRegistry.getRandom());

        //setAspectRatio(WCAspectRatioRegistry.getRandom());
    }

    public WCColorScheme getColorScheme()
    {
        return colorScheme;
    }

    public void setColorScheme(WCColorScheme colorScheme)
    {
        this.colorScheme = colorScheme;
    }

    public WCLayoutAlgo getLayoutAlgorithm()
    {
        return layoutAlgorithm;
    }

    public void setLayoutAlgorithm(WCLayoutAlgo layoutAlgorithm)
    {
        this.layoutAlgorithm = layoutAlgorithm;
    }

    public WCSimilarityAlgo getSimilarityAlgorithm()
    {
        return similarityAlgorithm;
    }

    public void setSimilarityAlgorithm(WCSimilarityAlgo similarityAlgorithm)
    {
        this.similarityAlgorithm = similarityAlgorithm;
    }

    public int getWordCount()
    {
        return wordCount;
    }

    public void setWordCount(int wordCount)
    {
        this.wordCount = wordCount;
    }

    public WCRankingAlgo getRankingAlgorithm()
    {
        return rankingAlgorithm;
    }

    public void setRankingAlgorithm(WCRankingAlgo rankingAlgorithm)
    {
        this.rankingAlgorithm = rankingAlgorithm;
    }

    public WCFont getFont()
    {
        return font;
    }

    public void setFont(WCFont font)
    {
        this.font = font;
    }

    public WCAspectRatio getAspectRatio()
    {
        return aspectRatio;
    }

    public void setAspectRatio(WCAspectRatio ar)
    {
        this.aspectRatio = ar;
    }

    public boolean isRemoveStopwords()
    {
        return removeStopwords;
    }

    public void setRemoveStopwords(boolean removeStopwords)
    {
        this.removeStopwords = removeStopwords;
    }

    public boolean isStemWords()
    {
        return stemWords;
    }

    public void setStemWords(boolean stemWords)
    {
        this.stemWords = stemWords;
    }

    public boolean isRemoveNumbers()
    {
        return removeNumbers;
    }

    public void setRemoveNumbers(boolean removeNumbers)
    {
        this.removeNumbers = removeNumbers;
    }

    public int getMinWordLength()
    {
        return minWordLength;
    }

    public void setMinWordLength(int minWordLength)
    {
        this.minWordLength = minWordLength;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nwordCount:  " + wordCount + "\n");
        sb.append("layout:     " + layoutAlgorithm + "\n");
        sb.append("similarity: " + similarityAlgorithm + "\n");
        sb.append("ranking:    " + rankingAlgorithm + "\n");
        sb.append("colorTheme: " + colorScheme + "\n");
        sb.append("font: " + font.getName() + "\n");
        sb.append("aspectRatio: " + aspectRatio + "\n");
        return sb.toString();
    }
}
