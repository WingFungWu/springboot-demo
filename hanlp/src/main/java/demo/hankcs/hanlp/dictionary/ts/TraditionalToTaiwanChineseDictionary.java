/*
 * <summary></summary>
 * <author>Hankcs</author>
 * <email>me@hankcs.com</email>
 * <create-date>2016-08-30 AM10:29</create-date>
 *
 * <copyright file="SimplifiedToHongKongChineseDictionary.java" company="码农场">
 * Copyright (c) 2008-2016, 码农场. All Right Reserved, http://www.hankcs.com/
 * This source is subject to Hankcs. Please contact Hankcs to get more information.
 * </copyright>
 */
package demo.hankcs.hanlp.dictionary.ts;

import demo.hankcs.hanlp.HanLP;
import demo.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;

import java.util.TreeMap;

import static demo.hankcs.hanlp.utility.Predefine.logger;

/**
 * 繁体转台湾繁体
 * @author hankcs
 */
public class TraditionalToTaiwanChineseDictionary extends BaseChineseDictionary
{
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();
    static
    {
        long start = System.currentTimeMillis();
        String datPath = HanLP.Config.tcDictionaryRoot + "t2tw";
        if (!loadDat(datPath, trie))
        {
            TreeMap<String, String> t2tw = new TreeMap<String, String>();
            if (!load(t2tw, false, HanLP.Config.tcDictionaryRoot + "t2tw.txt"))
            {
                throw new IllegalArgumentException("繁体转台湾繁体加载失败");
            }
            trie.build(t2tw);
            saveDat(datPath, trie, t2tw.entrySet());
        }
        logger.info("繁体转台湾繁体加载成功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToTaiwanChinese(String traditionalTaiwanChineseString)
    {
        return segLongest(traditionalTaiwanChineseString.toCharArray(), trie);
    }

    public static String convertToTaiwanChinese(char[] traditionalTaiwanChineseString)
    {
        return segLongest(traditionalTaiwanChineseString, trie);
    }
}
