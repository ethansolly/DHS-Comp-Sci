import asyncio, discord, random, subprocess
from discord import *
from discord.ext import commands
from sympy import *
from sympy.parsing.sympy_parser import parse_expr

description = '''A bot with no soul'''
usinglatex = false
bot = commands.Bot(command_prefix='>', description=description)
vc = None

@bot.event
async def on_ready():
    print('Logged in as')
    print(bot.user.name)
    print(bot.user.id)
    print('------')

    opus.load_opus('libopus-0')
    bot.change_presence(game='python.io')

@bot.command()
async def parselatex(*eq : str):
    """Parses a latex expression"""
    eqs = ''
    for s in eq: eqs += s
    preview(eq, viewer='file', filename='latex.png')
    bot.send_file(Message(message).channel, 'latex.png')

@bot.command()
async def add(left : int, right : int):
    """Adds two numbers together."""
    await bot.say(left + right)

@bot.command()
async def normsolve(*eq : str):
    """Solves a basic equation"""
    eqs = ''
    x = symbols('x')
    
    for s in eq: eqs += s
    eqsplit = str(eqs).split("=")
    
    expr1 = parse_expr(eqsplit[0])
    expr2 = parse_expr(eqsplit[1])
    await bot.say(str(solveset(Eq(expr1, expr2), x)).replace("*","\*").replace("I","i"))

@bot.command()
async def diffsolve(*eq : str):
    """Solves a differential equation with functions f(x) and g(x)"""
    eqs = ''
    x = symbols('x')
    f, g = symbols('f g', cls=Function)
    
    for s in eq: eqs += s
    eqsplit = str(eqs).split("=")
    
    expr1 = parse_expr(eqsplit[0])
    expr2 = parse_expr(eqsplit[1])
    await bot.say(str(dsolve(Eq(expr1, expr2), f(x))).replace("*","\*").replace("I","i"))

@bot.command()
async def intsolve(*eq : str):
    """Solves a integral"""
    eqs = ''
    x = symbols('x')
    
    for s in eq: eqs += s
    eqsplit = str(eqs).split("d")
    
    expr1 = parse_expr(eqsplit[0])
    expr2 = parse_expr(eqsplit[1])
    await bot.say(str(integrate(expr1, expr2)).replace("*","\*").replace("I","i"))
    
@bot.command()
async def derive(*eq : str):
    """Evaluates a derivative"""
    eqs = ''
    x = symbols('x')
    
    for s in eq: eqs += s
    eqsplit = str(eqs).split("d")
    
    expr1 = parse_expr(eqsplit[0])
    expr2 = parse_expr(eqsplit[1])
    await bot.say(str(diff(expr1, expr2)).replace("*","\*").replace("I","i"))

@bot.command(pass_context=True)
async def play(ctx, sub : str, link : str ):
    """Plays a youtube video by a url or search; play -u [URL] and play -s [SEARCH], respectively"""
    global vc
    if sub is None:
        await bot.say('Invalid subcommand.')
    else:
        vchannel = discord.utils.get(ctx.message.server.channels, name='General', type=ChannelType.voice)
        vc = await bot.join_voice_channel(vchannel)
        if sub is 'q':
            if (Message(message).author.id == "170295923442843648"):
                if not bot.is_voice_connected:
                    vc = await bot.join_voice_channel(vchannel)
                player = await vc.create_ffmpeg_player(link)
                player.start()
            else:
                await bot.say("You can't do that!")
        elif sub is 'u':
            if not bot.is_voice_connected:
                vc = await bot.join_voice_channel(vchannel)
            player = await vc.create_ytdl_player(link)
            player.start()
        else:
            await bot.say('Invalid subcommand.')
    

@bot.command()
async def expand(*eq : str):
    """Expands a function around point a for n terms"""
    eqs = ''
    x = symbols('x')
    
    for s in eq: eqs += s
    eqsplit = str(eqs).split("a=")
    if (len(eqsplit) > 1): 
        param = str(eqsplit[1]).split("n=")
    else:
        param = ["0", "6"]
    
    expr1 = parse_expr(eqsplit[0])
    expr2 = parse_expr(param[0])
    expr3 = parse_expr(param[1])
    await bot.say(str(expr1.series(x, expr2, expr3)).replace("*","\*").replace("I","i"))
    
@bot.command()
async def roll(dice : str):
    """Rolls a dice in NdN format."""
    try:
        rolls, limit = map(int, dice.split('d'))
    except Exception:
        await bot.say('Format has to be in NdN!')
        return
    if limit > 256:
        result = 'Nice try!'
    else:
        result = ', '.join(str(random.randint(1, limit)) for r in range(rolls))
    await bot.say(result)

@bot.command(description='For when you wanna settle the score some other way')
async def choose(*choices : str):
    """Chooses between multiple choices."""
    await bot.say(random.choice(choices))

@bot.command()
async def repeat(times, content='repeating...'):
    """Repeats a message multiple times."""
    for i in range(int(times) if IsInt(times) else 2):
        await bot.say(content)

@bot.command()
async def joined(member : discord.Member):
    """Says when a member joined."""
    await bot.say('{0.name} joined in {0.joined_at}'.format(member))

@bot.group(pass_context=True)
async def cool(ctx):
    """Says if a user is cool.
    In reality this just checks if a subcommand is being invoked.
    """
    if ctx.invoked_subcommand is None:
        await bot.say('No, {0.subcommand_passed} is not cool'.format(ctx))

@cool.command(name='bot')
async def _bot():
    """Is the bot cool?"""
    await bot.say('Yes, the bot is cool.')

@bot.command()
async def guess():
    await bot.say('Guess a number between 1 to 10')

    def guess_check(m):
        return m.content.isdigit()

    guess = await bot.wait_for_message(timeout=5.0, check=guess_check)
    answer = random.randint(1, 10)
    if guess is None:
        fmt = 'Sorry, you took too long. It was {}.'
        await bot.say(fmt.format(answer))
        return
    if int(guess.content) == answer:
        await bot.say('You are right!')
    else:
        await bot.say('Sorry. It is actually {}.'.format(answer))    

bot.run() # put id here

def IsInt(s):
    try: 
        int(s)
        return True
    except ValueError:
        return False
