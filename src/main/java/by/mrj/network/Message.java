package by.mrj.network;

import by.mrj.domain.types.Command;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@ToString
@Builder
@EqualsAndHashCode
public class Message implements Serializable {

    @NonNull
    Command command; //?
    //    long length;
    @NonNull
    String checksum;
    @NonNull
    Serializable payload;
}
