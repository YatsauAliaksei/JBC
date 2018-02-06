package by.mrj.structure;

import by.mrj.domain.UniqueTypedAsset;
import by.mrj.domain.trx.OutPoint;
import by.mrj.domain.trx.Transaction;
import by.mrj.domain.trx.TrxIn;
import by.mrj.domain.trx.TrxOut;

import java.util.List;
import java.util.Set;
import org.junit.Test;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import static by.mrj.util.CryptoUtil.doubleSha256;
import static org.assertj.core.api.Assertions.assertThat;

public class MerkleTreeTest {

    @Test
    public void proof_Even_Success() {
        Set<Transaction> trxs = Sets.newLinkedHashSet(Lists.newArrayList(
                createTrx(123),
                createTrx(234),
                createTrx(345),
                createTrx(456)));

        MerkleTree merkleTree = new MerkleTree(trxs);
        List<String> proofHashes = merkleTree.proof(createTrx(234).hash());
        assertThat(proofHashes).containsExactly(
                createTrx(123).hash(),
                doubleSha256(createTrx(345).hash() + createTrx(456).hash())
        );
    }

    @Test
    public void proof_Odd_Success() {
        Set<Transaction> trx = Sets.newLinkedHashSet(Lists.newArrayList(
                createTrx(123),
                createTrx(234),
                createTrx(345)));

        MerkleTree merkleTree = new MerkleTree(trx);
        List<String> proofHashes = merkleTree.proof(createTrx(345).hash());
        assertThat(proofHashes).containsExactly(
                createTrx(345).hash(),
                doubleSha256(createTrx(123).hash() + createTrx(234).hash())
        );
    }

    @Test
    public void rootHash_Even_Success() {
        Set<Transaction> trxs = Sets.newLinkedHashSet(Lists.newArrayList(
                createTrx(123),
                createTrx(234),
                createTrx(345),
                createTrx(456)));

        String part1 = createTrx(123).hash() + createTrx(234).hash();
        String part2 = createTrx(345).hash() + createTrx(456).hash();
        String root = doubleSha256(doubleSha256(part1) + doubleSha256(part2));

        MerkleTree merkleTree = new MerkleTree(trxs);
        assertThat(merkleTree.rootHash()).isEqualTo(root);
    }

    @Test
    public void rootHash_Odd_Success() {
        Set<Transaction> trxs = Sets.newLinkedHashSet(Lists.newArrayList(
                createTrx(123),
                createTrx(234),
                createTrx(345)));

        String part1 = createTrx(123).hash() + createTrx(234).hash();
        String part2 = createTrx(345).hash() + createTrx(345).hash();
        String root = doubleSha256(doubleSha256(part1) + doubleSha256(part2));

        MerkleTree merkleTree = new MerkleTree(trxs);
        assertThat(merkleTree.rootHash()).isEqualTo(root);
    }

    private Transaction createTrx(float value) {
        return Transaction.builder()
                .inCount(1)
                .trxInputs(Sets.newHashSet(TrxIn.builder().outPoint(OutPoint.builder().build()).build()))
                .outCount(1)
                .trxOutputs(Sets.newHashSet(TrxOut.builder().value(UniqueTypedAsset.builder().amount(value).build()).build()))
                .version(1).build();
    }
}