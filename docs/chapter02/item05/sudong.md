# 05 자원을 직접 명시하지 말고 의존객체 주입을 사용하라
SpellChecker를 만든다고 생각해보자.

```java
import java.util.Dictionary;

public class SpellChecker {
    private final Lexion dictionary = new 한글사전();

    private SpellChecker() {
    }
    //...
}
```
이렇게 하면 spellChecker의 `dictionary를 교체`하고 싶을 때 한계가 발생한다.

이런 경우 `유연성`을 위해서 생성시에 `의존객체 주입`을 사용하자
```java
public class SpellChcker{
    private final Lexicon dictionary;

    public SpellChcker(Lexicon dictionary) {
        this.dictionary = Object.requireNonNull(dictionary);
    }
    //...
}
```
한번 생성하면 불변인 점도 좋다.

### 자원 팩토리 생성
생성자에 자원 팩토리를 넘겨줄 수도 있다.
```java
public interface Tile {
    void draw();
}

public class SquareTile implements Tile {
    @Override
    public void draw() {
        System.out.println("Drawing a square tile");
    }
}

public class HexagonalTile implements Tile {
    @Override
    public void draw() {
        System.out.println("Drawing a hexagonal tile");
    }
}
```
```java
import java.util.function.Supplier;

public class Mosaic {
    private final Supplier<? extends Tile> tileFactory;

    public Mosaic(Supplier<? extends Tile> tileFactory) {
        this.tileFactory = tileFactory;
    }

    public void createTile() {
        Tile tile = tileFactory.get();
        tile.draw();
    }
}
```
```java
public class Main {
    public static void main(String[] args) {
        // SquareTile을 생성하는 팩토리
        Supplier<? extends Tile> squareTileFactory = SquareTile::new;
        Mosaic squareMosaic = new Mosaic(squareTileFactory);
        squareMosaic.createTile(); // Output: Drawing a square tile

        // HexagonalTile을 생성하는 팩토리
        Supplier<? extends Tile> hexagonalTileFactory = HexagonalTile::new;
        Mosaic hexagonalMosaic = new Mosaic(hexagonalTileFactory);
        hexagonalMosaic.createTile(); // Output: Drawing a hexagonal tile
    }
}
```

> 질문1 ) mosaic 생성시에 자원 팩토리를 넘겨주면 Tile을 바로 넘기는 것과 어떤 차이가 있나요?
> 객체 생성이 지연될 수 있는 장점이 있습니다.
>
> 질문2) 객체생성 지연은 어떨 때 좋나요.
> 크기가 큰 객체의 경우 생성 지연을 통해 에플리케이션 초기 로딩시간을 줄일 수 있습니다.
> 언제, 얼마나 생성되는지 파악할 수 있어서 성능 최적화를 도모할 수 있습니다.
