package by.mrj.structure;

import by.mrj.domain.Asset;

import java.util.List;
import java.util.Set;
import org.junit.Test;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import static by.mrj.util.CryptoUtil.doubleSha256;
import static org.assertj.core.api.Assertions.assertThat;

public class MerkleTreeTest {

    //    @formatter:off                                                       Values calculated online:
    private final String dHash123 = doubleSha256("123");                   //    123 - 173af653133d964edfc16cafe0aba33c8f500a07f3ba3f81943916910c257705
    private final String dHash234 = doubleSha256("234");                   //    234 - f20259cbd5e27dd5c46820b3b978663453225d49396669808dbaa6498d16c89d
    private final String dHash345 = doubleSha256("345");                   //    345 - 1774084ceb46ffd63cc873b9f6f8a6811fde2ba4adf27564a90c9d2ccf8d796c
    private final String dHash456 = doubleSha256("456");                   //    456 - 7b569eacfdec67064506c43b931fed2e9ee6edf9e9c1240005f9ad1e0fc37cc9
    private final String dHash123_234 = doubleSha256(dHash123 + dHash234); //    123 + 234 - 088d5e84a782522815f6a910cfa624b425ee11b2ab5a28c9f6ac530baabad9f0
    private final String dHash345_456 = doubleSha256(dHash345 + dHash456); //    345 + 456 - 5e31333568c96ef5af03ee848030ec78df2bd305b17a319bdfabb4291d6df1f8
    //    @formatter:on

    @Test
    public void proof_Even_Success() {
        Set<Asset> assets = Sets.newLinkedHashSet(Lists.newArrayList(
                createAsset("123"),
                createAsset("234"),
                createAsset("345"),
                createAsset("456")));

        MerkleTree merkleTree = new MerkleTree(assets);
        List<String> proofHashes = merkleTree.proof(doubleSha256("234"));
        assertThat(proofHashes).containsExactly(
                "173af653133d964edfc16cafe0aba33c8f500a07f3ba3f81943916910c257705", // 123
                "5e31333568c96ef5af03ee848030ec78df2bd305b17a319bdfabb4291d6df1f8" // 345 + 456
        );
    }

    @Test
    public void proof_Odd_Success() {
        Set<Asset> assets = Sets.newLinkedHashSet(Lists.newArrayList(
                createAsset("123"),
                createAsset("234"),
                createAsset("345")));

        MerkleTree merkleTree = new MerkleTree(assets);
        List<String> proofHashes = merkleTree.proof(doubleSha256("345"));
        assertThat(proofHashes).containsExactly(
                "1774084ceb46ffd63cc873b9f6f8a6811fde2ba4adf27564a90c9d2ccf8d796c", // 345
                "088d5e84a782522815f6a910cfa624b425ee11b2ab5a28c9f6ac530baabad9f0" // 123 + 234
        );
    }

    @Test
    public void rootHash_Even_Success() {
        Set<Asset> assets = Sets.newLinkedHashSet(Lists.newArrayList(
                createAsset("123"),
                createAsset("234"),
                createAsset("345"),
                createAsset("456")));

        String root = doubleSha256(dHash123_234 + dHash345_456); //        (123 + 234) + (345 + 456) - a5219f581cd4c2a2f52e902ad3872fe0278d7fc21fc7591261b2a47a8de8d809

        assertThat(root).isEqualTo("a5219f581cd4c2a2f52e902ad3872fe0278d7fc21fc7591261b2a47a8de8d809");

        MerkleTree merkleTree = new MerkleTree(assets);
        assertThat(merkleTree.rootHash()).isEqualTo(root);
    }

    @Test
    public void rootHash_Odd_Success() {
        Set<Asset> assets = Sets.newLinkedHashSet(Lists.newArrayList(
                createAsset("123"),
                createAsset("234"),
                createAsset("345")));

        String dHash345_345 = doubleSha256(dHash345 + dHash345); //        345 + 345 - bbcbff454caf39c2d91af10b3da06fa3108836744e39da5967773438b161ce24
        String root = doubleSha256(dHash123_234 + dHash345_345); //        (123 + 234) + (345 + 345) - 3594d60f2fcb612dced2f4903bc386a5fe98d6d61ebf22e2aa5e3fa845283e10

        assertThat(root).isEqualTo("3594d60f2fcb612dced2f4903bc386a5fe98d6d61ebf22e2aa5e3fa845283e10");

        MerkleTree merkleTree = new MerkleTree(assets);
        assertThat(merkleTree.rootHash()).isEqualTo(root);
    }

    private Asset createAsset(String address) {
        return Asset.builder().address(address).build();
    }
}